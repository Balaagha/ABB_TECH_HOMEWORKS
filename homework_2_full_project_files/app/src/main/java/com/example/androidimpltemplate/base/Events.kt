package com.example.androidimpltemplate.base

sealed class Events {
  object Loading : Events()

  object Done : Events()
}
