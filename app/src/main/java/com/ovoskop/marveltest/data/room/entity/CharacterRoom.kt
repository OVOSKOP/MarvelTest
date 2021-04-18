package com.ovoskop.marveltest.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "_cache_character",
    indices = [Index(value = ["name"], unique = true)])
data class CharacterRoom(
    var name: String,
    var description: String,
    var thumbnail: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)