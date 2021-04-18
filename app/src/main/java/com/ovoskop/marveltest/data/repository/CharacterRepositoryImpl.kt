package com.ovoskop.marveltest.data.repository

import androidx.lifecycle.LiveData
import com.ovoskop.marveltest.data.room.dao.CharacterRoomDAO
import com.ovoskop.marveltest.data.room.entity.CharacterRoom
import com.ovoskop.marveltest.domain.entity.Character
import com.ovoskop.marveltest.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterRepositoryImpl(private val characterRoomDAO: CharacterRoomDAO) : CharacterRepository {
    override fun getByPrefix(prefix: String, offset: Int, limit: Int) : List<CharacterRoom> {
        return characterRoomDAO.getAllByPrefix(prefix, offset, limit)
    }

    override fun saveCharacters(characters: List<Character>) {
        for (character in characters) {
            val characterRoom = CharacterRoom(
                character.name ?: "",
                character.description ?: "",
                character.thumbnail ?: ""
            )
            CoroutineScope(Dispatchers.IO).launch {
                characterRoomDAO.insert(characterRoom)
            }
        }
    }
}