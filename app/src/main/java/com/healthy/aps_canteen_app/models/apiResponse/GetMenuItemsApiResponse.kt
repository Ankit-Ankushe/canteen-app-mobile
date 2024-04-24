package com.healthy.aps_canteen_app.models.apiResponse

data class GetMenuItemsApiResponse(
  var list : List<MenuItem>
)

data class MenuItem(
  val id: Int = 0,
  val name: String = "",
  val category: String = "",
  val imageUrl: String ="",
  val rating: String = "",
  val price: String ="",
  val shortDescription: String ="",
)