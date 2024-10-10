package com.developer.edra.project_dev_bytes_9.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.developer.edra.project_dev_bytes_9.database.VideosDatabase
import com.developer.edra.project_dev_bytes_9.database.asDomainModel
import com.developer.edra.project_dev_bytes_9.domain.Video
import com.developer.edra.project_dev_bytes_9.network.Network
import com.developer.edra.project_dev_bytes_9.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class VideosRepository(private val database: VideosDatabase) {
    val videos: LiveData<List<Video>> =
        database.videoDao.getVideos().map {
            it.asDomainModel()
        }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = Network.devbytes.getPlaylist().await()
            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}