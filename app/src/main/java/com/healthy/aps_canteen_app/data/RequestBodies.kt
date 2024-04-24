package com.healthy.aps_canteen_app.data

import com.google.gson.annotations.SerializedName

data class ValidateUserRequestBody(
  @SerializedName("userName") val userName: String,
  @SerializedName("password") val password: String,
)