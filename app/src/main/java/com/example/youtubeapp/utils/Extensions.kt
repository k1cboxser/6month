package com.example.youtubeapp

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String, placeholder: Int = 0) {
    Glide.with(this.context)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}
