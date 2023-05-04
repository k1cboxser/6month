package com.example.youtubeapp.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapp.data.db.AppDatabase
import com.example.youtubeapp.data.remote.ApiService
import com.example.youtubeapp.data.remote.network.Resource
import com.example.youtubeapp.model.Playlist
import kotlinx.coroutines.Dispatchers

class Repository(private val apiService: ApiService, private val db: AppDatabase) {
    fun getPlayList(): LiveData<Resource<Playlist>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val result = apiService.getPlayList()
        if (result.isSuccessful) {
            emit(Resource.success(result.body()))
        } else {
            emit(Resource.error(result.message()))
        }
    }

    fun getPlaylistDetails(PlaylistId: String): LiveData<Resource<Playlist>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val result = apiService.getPlayListDetails(playlistId = PlaylistId)
            if (result.isSuccessful) {
                emit(Resource.success(result.body()))
            } else {
                emit(Resource.error(result.message()))
            }
        }

    fun insertPlaylist(playlist: Playlist?): LiveData<Resource<Boolean>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            if (playlist != null) {
                db.dao().insert(playlist)
                emit(Resource.success(true))
            } else {
                emit(Resource.error("data is null"))
            }
        }
}
