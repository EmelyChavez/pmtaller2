package com.pdmtaller2.c00097923_EmelyChavez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.pdmtaller2.c00097923_EmelyChavez.Navigation.Navigate
import com.pdmtaller2.c00097923_EmelyChavez.ui.screens.CategoryScreen
import com.pdmtaller2.c00097923_EmelyChavez.ui.screens.PreviewRestaurantScreen
import com.pdmtaller2.c00097923_EmelyChavez.ui.theme.FoodSpotByEchavezTheme
import com.pdmtaller2.c00097923_EmelyChavez.ui.viewmodel.RestaurantViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodSpotByEchavezTheme {
                Navigate()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodSpotByEchavezTheme {
        Greeting("Android")
    }
}