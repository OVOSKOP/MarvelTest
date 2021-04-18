package com.ovoskop.marveltest.data.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultDAO(
    @SerializedName("results")
    @Expose
    val results: List<CharacterDAO>? = null
)