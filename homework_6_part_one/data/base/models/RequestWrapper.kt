package com.example.data.base.models

import androidx.annotation.StringRes


object EmptyRequest

data class RequestWrapper<T>(
  val requestValue: T,
  val customHeaders: HashMap<String, String> = HashMap()
)