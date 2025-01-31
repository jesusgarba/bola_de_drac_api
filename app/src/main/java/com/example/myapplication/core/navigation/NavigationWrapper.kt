package com.example.myapplication.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplication.presentation.BolaDracApiViewModel
import com.example.myapplication.presentation.DetailScreen
import com.example.myapplication.presentation.InitScreen


@Composable
fun NavigationWrapper(bolaDracApiViewModel: BolaDracApiViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Initial) {
        composable<Initial> {
            InitScreen(bolaDracApiViewModel) { id ->
                navController.navigate(Detail(id))
            }
        }

        composable<Detail> { backStackEntry ->
            val detail = backStackEntry.toRoute<Detail>()
            DetailScreen(detail.id) { navController.navigateUp() }
        }
    }
}