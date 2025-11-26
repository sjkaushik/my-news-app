package com.kaushik.mynewsapp.presentation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kaushik.mynewsapp.presentation.head_lines.HeadLinesScreen
import com.kaushik.mynewsapp.presentation.home.HomeScreen
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
                    val context = LocalContext.current

                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {

                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(navController)
                        }

                        composable(route = Screen.TopHeadLinesScreen.route) {
                            HeadLinesScreen(onNewsClick = {
                                openCustomChromeTab(context = context, it)
                            })
                        }
                    }
                }
            }
        }
    }
}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}