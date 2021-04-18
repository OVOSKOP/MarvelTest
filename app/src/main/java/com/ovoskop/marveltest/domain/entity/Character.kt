package com.ovoskop.marveltest.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Character(
    var name: String?,
    var description: String?,
    var thumbnail: String?
)