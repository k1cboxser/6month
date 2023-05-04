package com.example.youtubeapp.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapp.databinding.PlaylistItemBinding
import com.example.youtubeapp.loadImage
import com.example.youtubeapp.model.Item

class PlaylistDetailsAdapter(private val onClick: (item: Item) -> Unit) :
    RecyclerView.Adapter<PlaylistDetailsAdapter.PlaylistDetailsViewHolder>() {

    private var list: ArrayList<Item> = arrayListOf()

    fun getList(lst: List<Item>) {
        list.clear()
        list.addAll(lst)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistDetailsViewHolder {
        val binding = PlaylistItemBinding.inflate(LayoutInflater.from(parent.context))
        return PlaylistDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistDetailsViewHolder, position: Int) {
        holder.onInit(list[position])
        holder.itemView.setOnClickListener {
            onClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    inner class PlaylistDetailsViewHolder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onInit(item: Item) {
            binding.tvNameVideo.text = item.snippet?.title.toString()
            binding.tvVideoSeries.text = "${item.contentDetails?.itemCount}"
            binding.imgPlaylist.loadImage(item.snippet?.thumbnails?.medium?.url.toString())
        }
    }
}