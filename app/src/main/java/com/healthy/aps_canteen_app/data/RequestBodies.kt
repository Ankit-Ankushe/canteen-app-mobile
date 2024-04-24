package com.healthy.aps_canteen_app.data

import com.google.gson.annotations.SerializedName

data class ValidateUserRequestBody(
  @SerializedName("username") val username: String,
  @SerializedName("password") val password: String,
)