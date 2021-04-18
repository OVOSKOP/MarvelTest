package com.ovoskop.marveltest.domain.interactor

import androidx.lifecycle.LiveData
import com.ovoskop.marveltest.data.room.entity.CharacterRoom
import com.ovoskop.marveltest.domain.entity.Character
import com.ovoskop.marveltest.domain.repository.CharacterRepository
import com.ovoskop.marveltest.presentation.interactor.CharacterInteractor

class CharacterInteractorImpl(private val characterRepository: CharacterRepository) : CharacterInteractor {
    override fun getByPrefix(prefix: String, offset: Int, limit: Int): List<Character> {
        val list: MutableList<Character> = mutableListOf()
        val characters = characterRepository.getByPrefix(prefix, offset, limit)

        for (character in characters) {
            val c = Character(
                character.name,
                character.description,
                character.thumbnail
            )
            list.add(c)
        }

        return list
    }

    override fun insertCache(characters: List<Character>) {
        characterRepository.saveCharacters(characters)
    }
}