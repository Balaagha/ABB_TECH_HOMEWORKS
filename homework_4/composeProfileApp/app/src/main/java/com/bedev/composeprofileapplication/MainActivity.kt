package com.bedev.composeprofileapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bedev.composeprofileapplication.feature.profile.ProfileScreen
import com.bedev.composeprofileapplication.feature.register.RegisterScreen
import com.bedev.composeprofileapplication.core.model.Profile
import com.bedev.composeprofileapplication.ui.theme.ComposeProfileApplicationTheme
import com.bedev.composeprofileapplication.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val profile = Profile()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProfileApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.RegisterScreen.route
                    ) {
                        composable(route = Screen.RegisterScreen.route) {
                            RegisterScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.ProfileScreen.route,
                        ) {
                            ProfileScreen(
                                navController = navController
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeProfileApplicationTheme {
        Greeting("Android")
    }
}