package com.example.androidimpltemplate.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.androidimpltemplate.utils.helper.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

  val _events = SingleLiveEvent<Events>()
  val events = _events as LiveData<Events>

}