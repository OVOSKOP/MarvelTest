package com.ovoskop.marveltest.data.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataDAO(
    @SerializedName("data")
    @Expose
    val data: ResultDAO? = null
)