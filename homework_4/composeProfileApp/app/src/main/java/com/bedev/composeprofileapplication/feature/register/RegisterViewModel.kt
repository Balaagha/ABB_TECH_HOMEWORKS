package com.bedev.composeprofileapplication.feature.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bedev.composeprofileapplication.core.event.UiEvent
import com.bedev.composeprofileapplication.data.datasource.ProfileDataDataSource
import com.bedev.composeprofileapplication.utils.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _name = mutableStateOf(
        TextFieldState(
            hint = "Enter name..."
        )
    )
    val name: State<TextFieldState> = _name

    private val _lastName = mutableStateOf(
        TextFieldState(
            hint = "Enter last name..."
        )
    )
    val lastName: State<TextFieldState> = _lastName

    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.EnteredName -> {
                _name.value = name.value.copy(
                    text = event.value
                )
            }
            is RegisterScreenEvent.ChangeEnteredNameFocus -> {
                _name.value = name.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            name.value.text.isBlank()
                )
            }
            is RegisterScreenEvent.EnteredLastName -> {
                _lastName.value = lastName.value.copy(
                    text = event.value
                )
            }
            is RegisterScreenEvent.ChangeEnteredLastNameFocus -> {
                _lastName.value = lastName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            lastName.value.text.isBlank()
                )
            }
        }
    }

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun saveProfileData() {
        if (name.value.text.isBlank() || lastName.value.text.isBlank()) {
            viewModelScope.launch {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = "You have to fill all field"
                    )
                )
            }
        } else {
            ProfileDataDataSource.setProfileData(
                firstNameValue = name.value.text,
                lastNameValue = lastName.value.text
            )
            viewModelScope.launch {
                _eventFlow.emit(
                    UiEvent.Navigate(Screen.ProfileScreen.route)
                )
            }
        }
    }

}