package com.bedev.composeprofileapplication.feature.register

import androidx.compose.ui.focus.FocusState

sealed class RegisterScreenEvent{
    data class EnteredName(val value: String): RegisterScreenEvent()
    data class ChangeEnteredNameFocus(val focusState: FocusState): RegisterScreenEvent()
    data class EnteredLastName(val value: String): RegisterScreenEvent()
    data class ChangeEnteredLastNameFocus(val focusState: FocusState): RegisterScreenEvent()
}

