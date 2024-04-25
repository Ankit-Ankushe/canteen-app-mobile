package com.healthy.aps_canteen_app.ui.viewModel

import com.healthy.aps_canteen_app.models.PlateItem
import com.healthy.aps_canteen_app.models.apiResponse.GetOrderHistoryApiResponse
import com.healthy.aps_canteen_app.models.apiResponse.MenuItem

data class AppState(
  val userName : String = "",
  val userId : Int = 0,
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
  val showLoader : Boolean = false,
  val plateItems : List<PlateItem> = listOf(),
  val totalAmount : Int = 0,
  val orderHistory :List<GetOrderHistoryApiResponse> = listOf()
)
