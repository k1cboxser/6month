package com.example.youtubeapp.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapp.databinding.PlaylistItemBinding
import com.example.youtubeapp.loadImage
import com.example.youtubeapp.model.Item

class PlaylistAdapter(private val onClick: (item: Item) -> Unit) :
    RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {
    private var list: MutableList<Item> = ArrayList()

    fun setList(lst: List<Item>) {
        list.clear()
        list.addAll(lst)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val binding = PlaylistItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PlaylistViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            onClick(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class PlaylistViewHolder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(playlist: Item) {
            binding.tvNameVideo.text = playlist.snippet?.title.toString()
            binding.tvVideoSeries.text = "${playlist.contentDetails?.itemCount}"
            binding.imgPlaylist.loadImage(playlist.snippet?.thumbnails?.medium?.url.toString())

        }
    }
}