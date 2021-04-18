package com.ovoskop.marveltest.data.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ovoskop.marveltest.domain.entity.Character

data class CharacterDAO(
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: ThumbnailsDAO? = null
) {
    fun toCharacter(): Character {
        return Character(name, description,
            "${thumbnail?.path}.${thumbnail?.extension}")
    }
}