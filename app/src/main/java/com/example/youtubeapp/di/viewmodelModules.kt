package com.example.youtubeapp.di

import com.example.youtubeapp.ui.details.PlaylistDetailsViewModel
import com.example.youtubeapp.ui.player.PlayerViewModel
import com.example.youtubeapp.ui.playlist.PlaylistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel { PlaylistDetailsViewModel(get()) }
    viewModel { PlayerViewModel() }

}