package com.example.youtubeapp.di

import android.content.Context
import androidx.room.Room
import com.example.youtubeapp.data.db.AppDatabase
import org.koin.dsl.module

val dbModule = module {
    single { provideDb(get()) }
}

fun provideDb(applicationContext: Context): AppDatabase {
    return Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "database-name"
    ).build()
}