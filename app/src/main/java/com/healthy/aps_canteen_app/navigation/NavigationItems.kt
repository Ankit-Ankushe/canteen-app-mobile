package com.healthy.aps_canteen_app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.RoomService
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val label:  String, val icons: ImageVector)

sealed class ApsCanteenNavItems(val route: String, val label: String, val icons: ImageVector) {
  object Menu : NavigationItem("menu","Menu", Icons.Default.RestaurantMenu)
  object Plate : NavigationItem("plate","Plate", Icons.Default.RoomService)
}

sealed class ApsCanteenRouteItems(val route: String, val label: String, val icons: ImageVector){
  object Login : NavigationItem("login","Login",Icons.Default.DateRange)
  object MenuItemDetails : NavigationItem("details","Menu",Icons.Default.DateRange)
}