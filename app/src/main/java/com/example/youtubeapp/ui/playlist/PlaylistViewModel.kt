package com.example.youtubeapp.ui.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.youtubeapp.base.BaseViewModel
import com.example.youtubeapp.data.repo.Repository
import com.example.youtubeapp.model.Playlist


class PlaylistViewModel(private val repo: Repository) : BaseViewModel() {

    private val insertPlaylistDB = MutableLiveData<Playlist?>()

    val setPlaylist = insertPlaylistDB.switchMap {
        repo.insertPlaylist(it)
    }

    fun getPlayList() = repo.getPlayList()

}