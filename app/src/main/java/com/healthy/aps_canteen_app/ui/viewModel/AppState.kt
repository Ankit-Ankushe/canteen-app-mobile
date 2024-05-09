package com.healthy.aps_canteen_app.ui.viewModel

import com.healthy.aps_canteen_app.models.PlateItem
import com.healthy.aps_canteen_app.models.apiResponse.GetOrderHistoryApiResponse
import com.healthy.aps_canteen_app.models.apiResponse.MenuItem

data class AppState(
  val userName : String = "",
  val userId : Int = 0,
  val menuItems : List<MenuItem> = listOf(),
  val menuItemsCount :Int = 0,
  val breakfastCount : Int = 0,
  val lunchCount : Int = 0,
  val beveragesCount : Int = 0,
  val dessertCount : Int = 0,
  val breakfastItems : List<MenuItem> = listOf(),
  val lunchItems : List<MenuItem> = listOf(),
  val beverageItems : List<MenuItem> = listOf(),
  val dessertItems : List<MenuItem> = listOf(),
  val showLoader : Boolean = false,
  val plateItems : List<PlateItem> = listOf(),
  val totalAmount : Int = 0,
  val orderHistory :List<GetOrderHistoryApiResponse> = listOf(),
  val inviteMessage : String = "\uD83D\uDE80 Welcome to the APS Canteen App! \uD83C\uDF4E\n" +
          "\n" +
          "Transforming School Meal Management for a Healthier Experience\n" +
          "\n" +
          "\uD83C\uDF1F Discover the Stellar Features of Our App:\n" +
          "\n" +
          "\uD83C\uDFCE️ Efficiency Unleashed: Say goodbye to long lines! Our streamlined ordering zaps wait times, serving up quick and hassle-free meal selections. \uD83C\uDF7D️\n" +
          "\n" +
          "\uD83C\uDFA8 Customization at Your Fingertips: Crave variety? Personalize your plate with a rainbow of nutritious meal options that cater to every taste bud. \uD83E\uDD57\n" +
          "\n" +
          "\uD83D\uDD12 Security You Can Trust: Go cashless and worry-free! Our secure payment system shields every transaction, giving parents the ultimate peace of mind. \uD83D\uDCB3\n" +
          "\n" +
          "\uD83C\uDF31 Sustainability in Every Swipe: Love the planet with every pre-order. Our app slashes food wastage, nurturing eco-friendly habits in our school’s heart. \uD83C\uDF0D\n" +
          "\n" +
          "\uD83E\uDD1D Engagement That Matters: Your voice, your app. Our feedback and rating galaxy invites collaboration and growth, rocketing the user experience to new heights. \uD83C\uDF1F\n" +
          "\n" +
          "\uD83D\uDC6B Community Spirit: Join hands in crafting a healthier meal cosmos for our students, staff, and parents. It’s a shared journey towards well-being. \uD83E\uDD17\n" +
          "\n" +
          "\uD83C\uDD94 User-Friendly ID Orbit: Navigate the app universe with ease! Students’ IDs are the stars, linked from admission numbers for a seamless and personalized journey. \uD83C\uDF10\n" +
          "\n" +
          "\uD83D\uDC6A Parental Superpowers: Keep an eye on your little star’s meal adventures and spending. Our app beams transparency and control straight to your fingertips. \uD83D\uDCF1\n" +
          "\n"
)
