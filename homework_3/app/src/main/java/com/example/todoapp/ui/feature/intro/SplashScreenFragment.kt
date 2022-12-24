package com.example.todoapp.ui.feature.intro

import android.annotation.SuppressLint
import android.text.SpannableStringBuilder
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.framework.BaseFragment
import com.example.todoapp.databinding.FragmentSplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment :
    BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {


    override fun setup() {
        binding.apply {
            tvTitle.text = getTitleText()
            lottieAnimView.animate().apply {
                translationY(-1500F)
                duration = 750
                startDelay = 3000
            }
            tvTitle.animate().apply {
                alpha(0F)
                duration = 300
                startDelay = 3400
            }
            tvSubTitle.animate().apply {
                alpha(0F)
                duration = 300
                startDelay = 3600
                withEndAction {
                    viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                        navigationMainFragment()
                    }
                }
            }
        }
    }


    private fun getTitleText(): CharSequence? = SpannableStringBuilder()
        .color(ContextCompat.getColor(requireContext(), R.color.white)) {
            append("Note ")
        }
        .color(ContextCompat.getColor(requireContext(), R.color.red_01)) {
            append("App")
        }

    private fun navigationMainFragment() {
        findNavController().navigate(
            R.id.mainFragment,
            null,
            NavOptions.Builder()
                .setPopUpTo(R.id.splashScreenFragment, true)
                .build()
        )
    }


}