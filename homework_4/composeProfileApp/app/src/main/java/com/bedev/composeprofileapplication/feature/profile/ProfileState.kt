package com.bedev.composeprofileapplication.feature.profile

import com.bedev.composeprofileapplication.core.model.Profile
import com.bedev.composeprofileapplication.data.datasource.ProfileDataDataSource

data class ProfileState(
    val profileData: Profile = ProfileDataDataSource.getProfileData(),
)
