package com.zak.myleaguemainscompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailReward : Screen("home/{champId}") {
        fun createRoute(champId: Long) = "home/$champId"
    }
}
