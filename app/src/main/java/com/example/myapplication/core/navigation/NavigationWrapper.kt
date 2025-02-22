package com.example.myapplication.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplication.presentation.homeScreen.HomeViewModel
import com.example.myapplication.presentation.detailScreen.DetailScreen
import com.example.myapplication.presentation.homeScreen.HomeScreen


@Composable
fun NavigationWrapper(bolaDracApiViewModel: HomeViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(bolaDracApiViewModel) { id ->
                navController.navigate(Detail(id))
            }
        }

        composable<Detail> { backStackEntry ->
            val detail = backStackEntry.toRoute<Detail>()
            DetailScreen(detail.id) { navController.navigateUp() }
        }
    }
}