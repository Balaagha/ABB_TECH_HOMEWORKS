package com.bedev.composeprofileapplication.core.model

import com.bedev.composeprofileapplication.R


data class Profile(
    val isAdmin: Boolean = true,
    val firstName: String = "Balaagha",
    val lastName: String = "Alihumatov",
    val email: String = "b.alihummatov@gmail.com",
    val telephone: String = "+994777113377  ",
    val gender: String = "Man",
    val avatarUrl: Int = R.drawable.profile,
    val customerNo: String = "13141517",
)