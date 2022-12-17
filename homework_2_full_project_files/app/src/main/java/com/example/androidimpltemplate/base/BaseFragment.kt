package com.example.androidimpltemplate.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.utils.extentions.changeStatusBarVisibility

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> VB,
) : Fragment() {

    open var onBackPressed: () -> Unit = {}

    open var statusBarColor: Int? = null
    open var statusBarVisibility: Boolean? = null


    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed.invoke()
                isEnabled = false
                activity?.onBackPressed()
                statusBarVisibility?.let { isVisible ->
                    this@BaseFragment.changeStatusBarVisibility(!isVisible, R.color.white)
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        statusBarVisibility?.let { isVisible ->
            this.changeStatusBarVisibility(isVisible, statusBarColor ?: R.color.statusBarColor)
        } ?: kotlin.run {
            statusBarColor?.let {
                this.changeStatusBarVisibility(statusBarVisibility ?: true, statusBarColor)
            }
        }
        _binding = bindingInflater.invoke(inflater)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    abstract fun setup()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
