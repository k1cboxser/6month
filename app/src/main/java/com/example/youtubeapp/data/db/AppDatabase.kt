package com.example.youtubeapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.youtubeapp.model.Playlist

@androidx.room.TypeConverters(TypeConverter::class)
@Database(entities = [Playlist::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): PlaylistDao
}