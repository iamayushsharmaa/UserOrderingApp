package com.example.foodorderapp.view.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodorderapp.view.login.OtpVerificationScreen
import com.example.foodorderapp.view.login.PhoneSignIn
import com.example.foodorderapp.view.main.CategoryScreen
import com.example.foodorderapp.view.main.HomeScreen
import com.example.foodorderapp.view.main.SearchScreen
import com.example.foodorderapp.viewmodel.FirebaseViewModel
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun NavigationScreen(
    firebaseViewModel: FirebaseViewModel,
    isUserLogin: Boolean,
    firestore: FirebaseFirestore
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homeScreen"
    //if (isUserLogin) "homeScreen" else "signInScreen"
    ){
        composable("signInScreen") {
            PhoneSignIn(
                navController,
                firebaseViewModel = firebaseViewModel
            )
        }
        composable("otpVerificationScreen/{phoneNumber}",
            arguments = listOf(navArgument("phoneNumber"){
                type = NavType.StringType
            })
            ) {backStackEnry ->
            val phoneNumber = backStackEnry.arguments?.getString("phoneNumber")
            if (phoneNumber != null) {
                OtpVerificationScreen(navController, phoneNumber,firebaseViewModel,firestore)
            }
        }
        composable("homeScreen") {
            HomeScreen(navController)
        }
        composable("searchScreen") {
            SearchScreen()
        }
        composable("category/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category").orEmpty()
            CategoryScreen(
                navController,
                category = category
            )
        }

    }
}