package com.healthy.aps_canteen_app.ui.viewModel

import com.healthy.aps_canteen_app.models.apiResponse.MenuItem

data class AppState(
  val menuItems : List<MenuItem> = listOf(),
  val menuItemsCount :Int = 0,
  val breakfastCount : Int = 0,
  val lunchCount : Int = 0,
  val beveragesCount : Int = 0,
  val dessertCount : Int = 0,
  val breakfastItems : List<MenuItem> = listOf(),
  val lunchItems : List<MenuItem> = listOf(),
  val beverageItems : List<MenuItem> = listOf(),
  val dessertItems : List<MenuItem> = listOf(),
  val showLoader : Boolean = false
)
