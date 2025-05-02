package com.pdmtaller2.c00097923_EmelyChavez.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.pdmtaller2.c00097923_EmelyChavez.ui.screens.CartScreen
import com.pdmtaller2.c00097923_EmelyChavez.ui.screens.CategoryScreen
import com.pdmtaller2.c00097923_EmelyChavez.ui.screens.RestaurantScreen
import com.pdmtaller2.c00097923_EmelyChavez.ui.screens.SearchAll
import com.pdmtaller2.c00097923_EmelyChavez.ui.viewmodel.RestaurantViewModel

@Composable
fun Navigate() {
    val navController = rememberNavController()
    val restaurantViewModel: RestaurantViewModel = RestaurantViewModel()
    NavHost(navController = navController, startDestination = "categories") {
        composable("categories") {
            CategoryScreen(navController,restaurantViewModel)
        }
        composable("restaurant/{restaurantId}") { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getString("restaurantId")?.toInt() ?: 0
            RestaurantScreen(
                navController,
                restaurantId,
                restaurantViewModel
            )
        }
        composable("myorders"){
            CartScreen(restaurantViewModel, navController)
        }
        composable("searchall") {
            SearchAll(navController, restaurantViewModel)
        }
    }
}

