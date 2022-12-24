package com.example.todoapp.framework

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.todoapp.utils.helper.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel(
    val savedStateHandle: SavedStateHandle?,
    application: Application
) : AndroidViewModel(application) {

    /**
     * generic events for triggering one time on UI
     */
    val singleTimeUiEvent: SingleLiveEvent<GenericUiEvent> = SingleLiveEvent()

    /**
     * Top level exception handler for the included context
     */
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        customExceptionHandler(throwable)
    }

    protected fun launchSafe(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(context + exceptionHandler, start, block)
    }

    protected open fun customExceptionHandler(throwable: Throwable) {
        Log.e(TAG, throwable.localizedMessage ?: EXCEPTION_GENERIC_MESSAGE)
    }

    companion object {
        const val TAG = "TAG"
        const val EXCEPTION_GENERIC_MESSAGE = "TAG"
    }
}