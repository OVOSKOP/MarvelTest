package com.ovoskop.marveltest.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ovoskop.marveltest.data.room.dao.CharacterRoomDAO
import com.ovoskop.marveltest.data.room.entity.CharacterRoom

@Database(entities = [CharacterRoom::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDAO() : CharacterRoomDAO
}