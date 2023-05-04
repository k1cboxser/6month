package com.example.youtubeapp.di

import com.example.youtubeapp.data.repo.Repository
import org.koin.dsl.module

val repoModules = module {
    single { Repository(get(), get()) }
}