package com.example.youtubeapp.model

data class Item(
    var contentDetails: ContentDetails?,
    var etag: String?, // e6qRlNMZw6_3FHxG1tSJlhqnZWI
    var id: String?, // PLw_GrEpYBfsLAaBr4HIv0A1feeilfvq2u
    var kind: String?, // youtube#playlist
    var snippet: Snippet?
)