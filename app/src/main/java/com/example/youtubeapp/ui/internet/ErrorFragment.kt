package com.example.youtubeapp.ui.internet


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapp.base.BaseFragment
import com.example.youtubeapp.databinding.FragmentErrorBinding
import com.example.youtubeapp.ui.playlist.PlaylistViewModel


class ErrorFragment : BaseFragment<FragmentErrorBinding, PlaylistViewModel>() {
    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentErrorBinding {
        return FragmentErrorBinding.inflate(inflater, container, false)
    }

    override fun initViewModel() {
    }

    override fun initView() {
        val checkInternet = CheckInternet(requireContext())
        checkInternet.observe(this) { isConnected ->
            binding.btnTryAgain.setOnClickListener {
                if (isConnected) {


                }
            }
        }

    }
}