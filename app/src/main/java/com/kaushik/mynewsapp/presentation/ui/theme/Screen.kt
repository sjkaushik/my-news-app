package com.kaushik.mynewsapp.presentation.ui.theme

sealed class Screen(val route : String){
    object TopHeadLinesScreen : Screen(route = "head_lines_screen")
}