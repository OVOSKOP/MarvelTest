package com.ovoskop.marveltest.presentation.interactor

import androidx.lifecycle.LiveData
import com.ovoskop.marveltest.data.room.entity.CharacterRoom
import com.ovoskop.marveltest.domain.entity.Character

interface CharacterInteractor {
    fun getByPrefix(prefix: String, offset: Int, limit: Int): List<Character>
    fun insertCache(characters: List<Character>)
}