package com.example.todoapp.framework

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import com.example.todoapp.utils.extentions.observe
import com.google.android.material.snackbar.Snackbar
import kotlin.reflect.KClass

abstract class BaseMvvmFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val bindingInflater: (LayoutInflater) -> VB,
    viewModelClass: KClass<VM>,
) : BaseFragment<VB>(bindingInflater) {

    open val viewModelFactoryOwner: (() -> ViewModelStoreOwner) = { this }

    open val factoryProducer: ViewModelProvider.Factory by lazy { defaultViewModelProviderFactory }
    open val viewModel: VM by createViewModelLazy(
        viewModelClass,
        { viewModelFactoryOwner.invoke().viewModelStore },
        { factoryProducer }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        // Listen to events
        observe(viewModel.singleTimeUiEvent, ::handleGenericsUiActionEvents)

    }

    protected open fun handleGenericsUiActionEvents(uiActionEvent: GenericUiEvent?) {
        when (uiActionEvent) {
            is GenericUiEvent.Alert -> {
                showAlertViaBaseUiEvent(uiActionEvent)
            }
            is GenericUiEvent.SnackBar -> {
                showSnackBarViaBaseUiEvent(uiActionEvent)
            }
            is GenericUiEvent.Toast -> {
                showToastViaBaseUiEvent(uiActionEvent)
            }
            else -> {
                Log.e(BaseViewModel.TAG, "Unknown event handle $uiActionEvent ")
            }
        }
    }

    protected open fun showToastViaBaseUiEvent(uiActionEvent: GenericUiEvent.Toast) {
        // No Implement yet
    }

    protected open fun showSnackBarViaBaseUiEvent(uiActionEvent: GenericUiEvent.SnackBar) {
        Snackbar.make(binding.root,uiActionEvent.title ?: EMPTY,Snackbar.LENGTH_LONG)
            .show()
    }

    protected open fun showAlertViaBaseUiEvent(uiActionEvent: GenericUiEvent.Alert) {
        // No Implement yet
    }

    companion object {
        private const val EMPTY = ""
    }

}
