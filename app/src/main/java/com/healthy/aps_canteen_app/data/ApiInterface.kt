package com.healthy.aps_canteen_app.data

import com.healthy.aps_canteen_app.models.apiResponse.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
public interface ApiInterface {
  @POST("/login")
  suspend fun validateUser(
    @Body requestBody : ValidateUserRequestBody
  ): Response<ValidateUserApiResponse>

  @GET("/menu")
  suspend fun getMenuItems():Response<List<MenuItem>>
  @POST("/placeOrder")
  suspend fun placeOrder(
    @Body requestBody: PlaceOrderRequestBody
  ):Response<PlaceOrderApiResponse>
  @GET("/order-history/{userId}")
  suspend fun getOrderHistoryByUserId(
    @Path("userId") userId: Int,
  ):Response<List<GetOrderHistoryApiResponse>>

}

object ApiInterfaceFactory {
  //Canteen App Api //
  val instance: ApiInterface by lazy {
    RetrofitHttpClient.instance.create(ApiInterface::class.java)
  }
}
