package com.example.youtubeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Playlist(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var etag: String?, // v8MclosYyUUSN2Iq2JNsdkQ865g
    var items: List<Item>,
    var kind: String?, // youtube#playlistListResponse
    var pageInfo: PageInfo?
)