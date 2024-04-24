package com.healthy.aps_canteen_app.models.apiResponse

data class ValidateUserApiResponse(
  val message: String ="",
  val userName :String ="",
  val userId : Int = 0
)