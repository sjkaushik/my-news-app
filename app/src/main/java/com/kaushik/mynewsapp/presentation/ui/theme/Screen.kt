package com.kaushik.mynewsapp.presentation.ui.theme

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home")
    object TopHeadLinesScreen : Screen(route = "head_lines_screen")
}