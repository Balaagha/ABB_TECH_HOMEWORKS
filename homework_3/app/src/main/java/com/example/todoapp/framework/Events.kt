package com.example.todoapp.framework

sealed class Events {
  object Loading : Events()

  object Done : Events()
}
