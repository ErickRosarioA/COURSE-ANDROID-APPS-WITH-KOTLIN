package com.developer.edra.project_gdg_finder_10.search

import android.location.Location
import com.developer.edra.project_gdg_finder_10.network.GdgApiService
import com.developer.edra.project_gdg_finder_10.network.GdgChapter
import com.developer.edra.project_gdg_finder_10.network.GdgResponse
import com.developer.edra.project_gdg_finder_10.network.LatLong
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class GdgChapterRepository(gdgApiService: GdgApiService) {


    private val request = gdgApiService.getChapters()


    private var inProgressSort: Deferred<SortedData>? = null

    var isFullyInitialized = false
        private set


    suspend fun getChaptersForFilter(filter: String?): List<GdgChapter> {
        val data = sortedData()
        return when (filter) {
            null -> data.chapters
            else -> data.chaptersByRegion.getOrElse(filter) { emptyList() }
        }
    }


    suspend fun getFilters(): List<String> = sortedData().filters


    private suspend fun sortedData(): SortedData = withContext(Dispatchers.Main) {

        inProgressSort?.await() ?: doSortData()
    }


    private suspend fun doSortData(location: Location? = null): SortedData {

        val result = coroutineScope {
            // launch a new coroutine to do the sort (so other requests can wait for this sort to complete)
            val deferred = async { SortedData.from(request.await(), location) }
            // cache the Deferred so any future requests can wait for this sort
            inProgressSort = deferred
            // and return the result of this sort
            deferred.await()
        }
        return result
    }


    suspend fun onLocationChanged(location: Location) {

        withContext(Dispatchers.Main) {
            isFullyInitialized = true


            inProgressSort?.cancel()

            doSortData(location)
        }
    }


    private class SortedData private constructor(
        val chapters: List<GdgChapter>,
        val filters: List<String>,
        val chaptersByRegion: Map<String, List<GdgChapter>>
    ) {

        companion object {

            suspend fun from(response: GdgResponse, location: Location?): SortedData {
                return withContext(Dispatchers.Default) {
                    // this sorting is too expensive to do on the main thread, so do thread confinement here.
                    val chapters: List<GdgChapter> = response.chapters.sortByDistanceFrom(location)
                    // use distinctBy which will maintain the input order - this will have the effect of making
                    // a filter list sorted by the distance from the current location
                    val filters: List<String> = chapters.map { it.region }.distinctBy { it }
                    // group the chapters by region so that filter queries don't require any work
                    val chaptersByRegion: Map<String, List<GdgChapter>> =
                        chapters.groupBy { it.region }
                    // return the sorted result
                    SortedData(chapters, filters, chaptersByRegion)
                }

            }



            private fun List<GdgChapter>.sortByDistanceFrom(currentLocation: Location?): List<GdgChapter> {
                currentLocation ?: return this

                return sortedBy { distanceBetween(it.geo, currentLocation) }
            }


            private fun distanceBetween(start: LatLong, currentLocation: Location): Float {
                val results = FloatArray(3)
                Location.distanceBetween(
                    start.lat,
                    start.long,
                    currentLocation.latitude,
                    currentLocation.longitude,
                    results
                )
                return results[0]
            }
        }
    }
}