package com.pdmtaller2.c00097923_EmelyChavez.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.pdmtaller2.c00097923_EmelyChavez.data.dummy.allRestaurant
import com.pdmtaller2.c00097923_EmelyChavez.data.model.Dish
import com.pdmtaller2.c00097923_EmelyChavez.data.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RestaurantViewModel : ViewModel() {
    private val _restaurantsByCategory = MutableStateFlow<Map<String, List<Restaurant>>>(emptyMap())
    val restaurantsByCategory: StateFlow<Map<String, List<Restaurant>>> = _restaurantsByCategory
    private val _allRestaurants = allRestaurant
    private val _orderHistoryId = mutableStateListOf<Int>()
    private val _orderHistoryStateFlow = MutableStateFlow<List<Int>>(emptyList())
    val orderHistory: StateFlow<List<Int>> get() = _orderHistoryStateFlow
    private val _allDishes = allRestaurant.flatMap{it.menu}
    private val _searchState = MutableStateFlow("")
    val searchState: StateFlow<String> = _searchState


    init {
        categorizeRestaurants(_allRestaurants)
    }
    
    fun categorizeRestaurants(allRestaurant: List<Restaurant>) {
        val categoryMap = mapOf(
            "Comida Rápida" to "comida rápida",
            "Comida Mexicana" to "comida mexicana",
            "Comida Italiana" to "comida italiana",
            "Comida Asiática" to "comida asiática",
            "Comida Saludable" to "comida saludable",
            "Postres y Dulces" to "postres",
            "Bebidas" to "bebidas"
        )

        val restaurantsByCategory = mutableMapOf<String, MutableList<Restaurant>>()
        categoryMap.keys.forEach { category -> restaurantsByCategory[category] = mutableListOf() }

        for (restaurant in allRestaurant) {
            val restaurantCategoriesLower = restaurant.categories.map { it.lowercase() }

            categoryMap.forEach { (category, categoryValue) ->
                if (restaurantCategoriesLower.any { it.contains(categoryValue) }) {
                    restaurantsByCategory[category]?.add(restaurant)
                }
            }
        }

        _restaurantsByCategory.value = restaurantsByCategory
    }

    fun getRestaurantById(restaurantId: Int): Restaurant? {
        return _allRestaurants.find { it.id == restaurantId }
    }

    fun getDishesById(id: Int) :Dish?{
        return _allDishes.find { it.id == id }
    }

    fun updateSearchQuery(searchQuery: String){
        _searchState.value=searchQuery
    }

    fun addToCart(dish: Dish){
        _orderHistoryId.add(dish.id)
        _orderHistoryStateFlow.value= _orderHistoryId.toList()
    }

    fun advancedSearcher(searchQuery: String): List<Restaurant>{
        return _allRestaurants.filter { restaurant ->
            val findName = restaurant.name.contains(searchQuery, ignoreCase = true)

            val findCategory = restaurant.categories.any{
                it.contains(searchQuery, ignoreCase = true)
            }
            val findDish = restaurant.menu.any {
                it.name.contains(searchQuery, ignoreCase = true)
            }
            val findDishDescription = restaurant.menu.any{
                it.description.contains(searchQuery, ignoreCase = true)
            }

            findName || findCategory || findDish || findDishDescription
        }
    }
}
