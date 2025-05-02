package com.pdmtaller2.c00097923_EmelyChavez.Navigation

sealed class Screen(val route:String){
    object Category: Screen("categories")
    object Restaurant: Screen("restaurant/{restauranrId}"){
        fun createRoute(restaurantId: Int) = "restaurant/$restaurantId"
    }
    object MyOrder: Screen("myorders")
    object searchAll: Screen("searchall")
}