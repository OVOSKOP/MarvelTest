package com.ovoskop.marveltest.data.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThumbnailsDAO {
    @SerializedName("path")
    @Expose
    var path: String? = null
    @SerializedName("extension")
    @Expose
    var extension: String? = null
}