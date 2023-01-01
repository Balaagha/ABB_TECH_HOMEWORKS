package com.bedev.composeprofileapplication.feature.register

data class TextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
