package com.healthy.aps_canteen_app.data

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class AuthInterceptor(private val token: String, private val userId: String) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request().newBuilder()
      .addHeader("Authentication-Token", "$token")
      .addHeader("userId","$userId")
      .build()
    return chain.proceed(request)
  }
}
object RetrofitHttpClient {
  val instance : Retrofit by lazy {
    Retrofit.Builder()
      .baseUrl("https://canteen-app-server.onrender.com//")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
  }
}