package com.ovoskop.marveltest.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ovoskop.marveltest.data.room.entity.CharacterRoom

@Dao
interface CharacterRoomDAO {

    @Query("SELECT * FROM _cache_character")
    fun getAll() : LiveData<List<CharacterRoom>>

    @Query("SELECT * FROM _cache_character WHERE name LIKE :prefix LIMIT :limit OFFSET :offset")
    fun getAllByPrefix(prefix: String, offset: Int, limit: Int) : List<CharacterRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: CharacterRoom)

    @Update
    fun update(character: CharacterRoom)

    @Delete
    fun delete(character: CharacterRoom)

}