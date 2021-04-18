package com.ovoskop.marveltest.domain.repository

import androidx.lifecycle.LiveData
import com.ovoskop.marveltest.data.room.entity.CharacterRoom
import com.ovoskop.marveltest.domain.entity.Character

interface CharacterRepository {
    fun getByPrefix(prefix: String, offset: Int, limit: Int): List<CharacterRoom>
    fun saveCharacters(characters: List<Character>)
}