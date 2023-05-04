package com.example.youtubeapp.ui.details


import com.example.youtubeapp.base.BaseViewModel
import com.example.youtubeapp.data.repo.Repository

class PlaylistDetailsViewModel(private val repo: Repository) : BaseViewModel() {

    fun getPlaylistDetails(playlistId: String) = repo.getPlaylistDetails(playlistId)
}