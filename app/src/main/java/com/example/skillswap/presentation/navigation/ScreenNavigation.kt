package com.example.skillswap.presentation.navigation

sealed class ScreenNavigation(val route: String) {
    object SkillScreen : ScreenNavigation("skill")
    object SwapOfferScreen : ScreenNavigation("swap")
}