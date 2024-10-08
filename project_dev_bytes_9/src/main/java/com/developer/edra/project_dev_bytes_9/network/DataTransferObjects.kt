
package com.developer.edra.project_dev_bytes_9.network

import com.developer.edra.project_dev_bytes_9.database.DatabaseVideo
import com.developer.edra.project_dev_bytes_9.domain.Video
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)


@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?)


fun NetworkVideoContainer.asDomainModel(): List<Video> {
    return videos.map {
        Video(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}

fun NetworkVideoContainer.asDatabaseModel(): Array<DatabaseVideo> {
    return videos.map {
        DatabaseVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }.toTypedArray()
}