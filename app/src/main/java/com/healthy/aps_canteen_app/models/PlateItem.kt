package com.healthy.aps_canteen_app.models

data class PlateItem(
  val id: Int = 0,
  val name: String = "",
  val category: String = "",
  val imageUrl: String ="",
  val rating: String = "",
  val price: String ="",
  val shortDescription: String ="",
  var quantity: Int = 0
)
