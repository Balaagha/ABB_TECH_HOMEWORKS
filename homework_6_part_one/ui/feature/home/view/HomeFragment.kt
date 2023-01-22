package com.example.todoapp.ui.feature.home.view

import android.widget.Toast
import androidx.core.view.isVisible
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.framework.BaseMvvmFragment
import com.example.todoapp.ui.feature.home.viewmodel.HomeViewModel
import com.example.todoapp.utils.extentions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseMvvmFragment<FragmentHomeBinding,HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class
) {


    override fun setup() {
        initViewListener()
        observe(viewModel.imageResult) {
            binding.imgView.setImageBitmap(it)
        }
        observe(viewModel.isLoading) {
            it?.let {
                binding.progressBar.isVisible = it
            }
        }
        observe(viewModel.message) {
            it?.let {
                Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViewListener() {
        binding.apply {
            downlaodBtn.setOnClickListener {
                viewModel.download()
            }
            cancelBtn.setOnClickListener {
                viewModel.cancelDownload()
            }
            retryBtn.setOnClickListener {
                viewModel.retryDownload()
            }
        }
    }


}