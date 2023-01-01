package com.bedev.composeprofileapplication.utils

sealed class Screen(val route: String) {
    object RegisterScreen: Screen("Register_screen")
    object ProfileScreen: Screen("profile_screen")
}
