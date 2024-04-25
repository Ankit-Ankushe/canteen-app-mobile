package com.healthy.aps_canteen_app.models.apiResponse

import com.healthy.aps_canteen_app.models.PlateItem

data class GetOrderHistoryApiResponse(
  val userId: Int,
  val timestamp: String,
  val items: List<PlateItem>
)