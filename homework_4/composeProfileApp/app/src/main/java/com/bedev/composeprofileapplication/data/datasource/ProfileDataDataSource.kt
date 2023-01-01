package com.bedev.composeprofileapplication.data.datasource

import com.bedev.composeprofileapplication.core.model.Profile

object ProfileDataDataSource {
    var profile = Profile()

    fun setProfileData(firstNameValue: String, lastNameValue: String){
        profile = Profile(
            firstName = firstNameValue,
            lastName = lastNameValue
        )
    }

    fun getProfileData(): Profile {
        return profile
    }
}