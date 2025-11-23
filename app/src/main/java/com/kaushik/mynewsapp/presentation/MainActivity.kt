package com.kaushik.mynewsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kaushik.mynewsapp.presentation.head_lines.HeadLinesScreen
import com.kaushik.mynewsapp.presentation.ui.theme.MyNewsAppTheme
import com.kaushik.mynewsapp.presentation.ui.theme.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNewsAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.TopHeadLinesScreen.route
                    ) {

                        composable(route = Screen.TopHeadLinesScreen.route) {
                            HeadLinesScreen(this@MainActivity)
                        }
                    }
                }
            }
        }
    }
}