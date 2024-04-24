package com.healthy.aps_canteen_app.models.apiErrorResponse

import com.google.gson.annotations.SerializedName

data class ErrorMessage(val message: String)

data class Errors (

  @SerializedName("message"    ) var message    : String?           = null,
  @SerializedName("locations"  ) var locations  : ArrayList<String> = arrayListOf(),
  @SerializedName("extensions" ) var extensions : Extensions?       = Extensions()

)
data class Extensions (

  @SerializedName("classification" ) var classification : String? = null

)