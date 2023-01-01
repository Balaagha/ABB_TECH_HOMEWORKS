package com.bedev.composeprofileapplication.feature.profile

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bedev.composeprofileapplication.feature.register.RegisterViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavController,

) {

    val viewModel: ProfileViewModel = viewModel()

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

//    Image(
//        painter = painterResource(avatar),
//        contentDescription = "Contact profile picture",
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .size(200.dp)
//            .clip(CircleShape)
//            .layoutId(PROFILE_PIC_ID)
//    )
//    Text(
//        text = name,
//        modifier = Modifier.padding(PaddingValues(top = 20.dp, bottom = 10.dp))
//            .layoutId(USERNAME),
//        fontSize = 18.sp,
//        fontWeight = FontWeight.Bold
//    )
//    Text(text = type, modifier = Modifier.layoutId(TYPE))
    Scaffold(
        scaffoldState = scaffoldState
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(viewModel.state.value.profileData.avatarUrl),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "First Name -> " + viewModel.state.value.profileData.firstName,
                modifier = Modifier.padding(PaddingValues(top = 5.dp, bottom = 5.dp)),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Last Name -> " + viewModel.state.value.profileData.lastName,
                modifier = Modifier.padding(PaddingValues(top = 5.dp, bottom = 5.dp)),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "email -> " + viewModel.state.value.profileData.email,
                modifier = Modifier.padding(PaddingValues(top = 5.dp, bottom = 5.dp)),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "telephone -> " + viewModel.state.value.profileData.telephone,
                modifier = Modifier.padding(PaddingValues(top = 5.dp, bottom = 5.dp)),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "gender -> " + viewModel.state.value.profileData.gender,
                modifier = Modifier.padding(PaddingValues(top = 5.dp, bottom = 5.dp)),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "customerNo -> " + viewModel.state.value.profileData.customerNo,
                modifier = Modifier.padding(PaddingValues(top = 5.dp, bottom = 5.dp)),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "User " + if(viewModel.state.value.profileData.isAdmin) "is admin" else "is not admin" ,
                modifier = Modifier.padding(PaddingValues(top = 5.dp, bottom = 5.dp)),
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}