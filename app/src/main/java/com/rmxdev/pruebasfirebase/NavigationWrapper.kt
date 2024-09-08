package com.rmxdev.pruebasfirebase

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rmxdev.pruebasfirebase.presentation.home.HomeScreen
import com.rmxdev.pruebasfirebase.presentation.initial.InitialScreen
import com.rmxdev.pruebasfirebase.presentation.login.LoginScreen
import com.rmxdev.pruebasfirebase.presentation.signup.SignUpScreen

@Composable
fun NavigationWrapper(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    auth: FirebaseAuth
) {
    NavHost(navController = navHostController, startDestination = "home") {
        composable("initial") {
            InitialScreen(
                modifier = modifier,
                navigateToLogin = { navHostController.navigate("login") },
                navigateToSignUp = { navHostController.navigate("signup") }
            )
        }
        composable("login") {
            LoginScreen(modifier = modifier, auth){
                navHostController.navigate("home")
            }
        }
        composable("signup") {
            SignUpScreen(modifier = modifier, auth)
        }
        composable("home"){
            HomeScreen(modifier = modifier)
        }
    }
}