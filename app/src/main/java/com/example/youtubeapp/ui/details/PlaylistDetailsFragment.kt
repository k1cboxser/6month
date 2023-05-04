package com.example.youtubeapp.ui.details

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.youtubeapp.R
import com.example.youtubeapp.base.BaseFragment
import com.example.youtubeapp.data.remote.network.Status
import com.example.youtubeapp.databinding.FragmentDetailsPlaylistBinding
import com.example.youtubeapp.ui.internet.CheckInternet
import com.example.youtubeapp.ui.playlist.PlaylistFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistDetailsFragment : BaseFragment<FragmentDetailsPlaylistBinding,
        PlaylistDetailsViewModel>() {

    private lateinit var adapter: PlaylistDetailsAdapter

    override val viewModel: PlaylistDetailsViewModel by viewModel()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsPlaylistBinding {
        return FragmentDetailsPlaylistBinding.inflate(inflater, container, false)
    }

    override fun initViewModel() {
        val id = arguments?.getString(PlaylistFragment.PLAYLIST_KEY)
        val title = arguments?.getString(PlaylistFragment.PLAYLIST)
        viewModel.getPlaylistDetails(id.toString()).observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.loading.value = false
                    it.data?.let { it1 -> adapter.getList(it1.items) }
                }
                Status.ERROR -> {
                    viewModel.loading.value = false
                    Log.e("ololo", "initViewModel: " + it.msg)
                }
                Status.LOADING -> {
                    viewModel.loading.value = true
                    binding.decVideo.text = title.toString()
                }
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
    }

    override fun initView() {
        adapter = PlaylistDetailsAdapter {
            findNavController().navigate(
                R.id.playerFragment, bundleOf(
                    VIDEO_ID to
                            it.snippet?.resourceId?.videoId, TITLE to it.snippet?.title
                )
            )
        }
        val checkInternet = CheckInternet(requireContext())
        checkInternet.observe(this) { isConnected ->
            if (!isConnected) {
                findNavController().navigate(R.id.errorFragment)
            }
            binding.recyclerView.adapter = adapter
        }
    }

    companion object {
        const val VIDEO_ID = "videoId"
        const val TITLE = "title"
    }
}