package com.healthy.aps_canteen_app.data

import com.google.gson.annotations.SerializedName
import com.healthy.aps_canteen_app.models.PlateItem

data class ValidateUserRequestBody(
  @SerializedName("username") val username: String,
  @SerializedName("password") val password: String,
)

data class PlateItemRequestBody(
  @SerializedName("itemId") val itemId: Int,
  @SerializedName("name") val name: String,
  @SerializedName("category") val category: String,
  @SerializedName("imageUrl") val imageUrl: String,
  @SerializedName("rating") val rating: String,
  @SerializedName("price") val price: String,
  @SerializedName("shortDescription") val shortDescription: String,
  @SerializedName("qty") val qty: Int
)

data class PlaceOrderRequestBody(
  @SerializedName("userId") val userId: Int,
  @SerializedName("items") val items: List<PlateItem>
)