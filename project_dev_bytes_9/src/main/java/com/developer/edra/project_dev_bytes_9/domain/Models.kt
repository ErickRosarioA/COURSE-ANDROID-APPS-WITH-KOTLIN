
package com.developer.edra.project_dev_bytes_9.domain

import com.developer.edra.project_dev_bytes_9.util.smartTruncate


data class Video(val title: String,
                 val description: String,
                 val url: String,
                 val updated: String,
                 val thumbnail: String) {


    val shortDescription: String
        get() = description.smartTruncate(200)
}
