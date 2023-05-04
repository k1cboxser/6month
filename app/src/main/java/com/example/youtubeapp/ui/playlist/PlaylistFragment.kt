package com.example.youtubeapp.ui.playlist


import android.util.Log.e
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.youtubeapp.R
import com.example.youtubeapp.base.BaseFragment
import com.example.youtubeapp.data.remote.network.Status
import com.example.youtubeapp.databinding.FragmentPlaylistBinding
import com.example.youtubeapp.model.Item
import com.example.youtubeapp.ui.internet.CheckInternet
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlaylistFragment : BaseFragment<FragmentPlaylistBinding, PlaylistViewModel>() {
    private lateinit var adapter: PlaylistAdapter
    override val viewModel: PlaylistViewModel by viewModel()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlaylistBinding {
        return FragmentPlaylistBinding.inflate(inflater, container, false)
    }


    override fun initView() {
        adapter = PlaylistAdapter(this::onItemClick)
        binding.rvPlaylist.adapter = adapter
        val checkInternet = CheckInternet(requireContext())
        checkInternet.observe(this) { isConnected ->
            if (!isConnected) {
                findNavController().navigate(R.id.errorFragment)
            }
        }
    }

    private fun onItemClick(item: Item) {
        findNavController().navigate(
            R.id.playlistDetailsFragment, bundleOf(
                PLAYLIST_KEY to item.id, PLAYLIST to item.snippet?.title
            )
        )
    }

    override fun initViewModel() {
        viewModel.getPlayList().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.loading.value = false
                    it.data?.items?.let { it1 -> adapter.setList(it1) }
                }
                Status.ERROR -> {
                    viewModel.loading.value = false
                    e("lolo", "initViewModel: " + it.msg)
                }
                else -> {
                    viewModel.loading.value = true
                }
            }
        }
        viewModel.setPlaylist.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                Toast.makeText(requireContext(), "data saved", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progress.isVisible = it
        }
    }

    companion object {
        const val PLAYLIST_KEY = "key,id,playlist"
        const val PLAYLIST = "key,item.title"
    }
}