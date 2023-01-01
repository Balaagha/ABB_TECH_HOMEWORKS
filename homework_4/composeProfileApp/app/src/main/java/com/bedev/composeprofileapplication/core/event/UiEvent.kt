package com.bedev.composeprofileapplication.core.event

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
}