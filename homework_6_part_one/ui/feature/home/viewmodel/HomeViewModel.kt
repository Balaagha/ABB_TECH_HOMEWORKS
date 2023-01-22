package com.example.todoapp.ui.feature.home.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.todoapp.data.base.models.DataWrapper
import com.example.todoapp.data.feature.download.usecase.DownloadSampleImageUseCase
import com.example.todoapp.framework.BaseViewModel
import com.example.todoapp.utils.helper.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    applicationData: Application,
    private val downloadSampleImageUseCase: DownloadSampleImageUseCase
) : BaseViewModel(savedStateHandle, applicationData),CoroutineScope {

    var job: Job? = null
    override val coroutineContext: CoroutineContext =
        SupervisorJob() + Dispatchers.Main


    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var _imageResult: MutableLiveData<Bitmap?> = MutableLiveData<Bitmap?>()
    val imageResult: LiveData<Bitmap?> get() = _imageResult

    var message: SingleLiveEvent<String> = SingleLiveEvent()

    fun download(){
        if(isLoading.value != true){
            job = launch {
                _imageResult.value = null
                downloadSampleImageUseCase.invoke().collectLatest {
                    when(it){
                        is DataWrapper.Loading -> {
                            _isLoading.postValue( true)
                        }
                        is DataWrapper.Success -> {
                            _isLoading.value = false
                            _imageResult.value = it.invoke()
                        }
                        is DataWrapper.Failure -> {
                            _isLoading.value = false
                            message.postValue(it.message ?: "Error happening")
                        }
                    }
                }
            }
        }
    }

    fun retryDownload(){
        job?.cancel()
        download()
        message.postValue("retry download")
    }

    fun cancelDownload(){
        job?.cancel()
        _isLoading.value = false
        message.postValue("Canceled")
    }
}