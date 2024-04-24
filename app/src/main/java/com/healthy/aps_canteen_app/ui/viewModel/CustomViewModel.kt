package com.healthy.aps_canteen_app.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.healthy.aps_canteen_app.data.ApiInterfaceFactory
import com.healthy.aps_canteen_app.data.ValidateUserRequestBody
import com.healthy.aps_canteen_app.models.PlateItem
import com.healthy.aps_canteen_app.models.apiResponse.MenuItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(AppState())
  val _stateFlow: StateFlow<AppState> = _uiState.asStateFlow()
  private val retrofit = ApiInterfaceFactory.instance
  val gson = GsonBuilder().setPrettyPrinting().create()

  fun validateUser(userName: String, password: String, callback: (Boolean) -> Unit){
    viewModelScope.launch {
      val requestBody = ValidateUserRequestBody(userName,password)
      val res = retrofit.validateUser(requestBody)
      val gson = GsonBuilder().setPrettyPrinting().create()
      Log.d("login response",res.body().toString())

      if (res.isSuccessful) {
        val response = res.body()
        if (response?.message == "Login successful!") {
          _uiState.update { currentState: AppState ->
            val newState = currentState.copy(
              userName = response.userName,
              userId = response.userId
            )
            newState
          }
          callback(true)
        } else {
          callback(false)
        }
      } else {
        Log.d("error response", "${res.errorBody()}")
        callback(false)
      }
    }
  }

  fun fetchMenuItems(){
    _uiState.update { currentState: AppState ->
      val newState = currentState.copy(
        showLoader = true
      )
      newState
    }
    viewModelScope.launch {
      val response = retrofit.getMenuItems()
      val gson = GsonBuilder().setPrettyPrinting().create()
      if (response.isSuccessful) {
        val menuList = response.body()
        Log.d("response",menuList.toString())
        if (menuList != null) {
          val breakfastItems = menuList.filter { it.category == "Breakfast" }
          val lunchItems = menuList.filter { it.category == "Lunch" }
          val beverageItems = menuList.filter { it.category == "Beverages" }
          val dessertItems = menuList.filter { it.category == "Dessert" }

          _uiState.update { currentState: AppState ->
            val newState = currentState.copy(
              menuItems = menuList,
              breakfastItems = breakfastItems,
              lunchItems = lunchItems,
              beverageItems = beverageItems,
              dessertItems =  dessertItems,
              breakfastCount = breakfastItems.size,
              lunchCount = lunchItems.size,
              beveragesCount =  beverageItems.size,
              dessertCount = dessertItems.size,
              menuItemsCount = menuList.size,
              showLoader = false
            )
            newState
          }
        }
      } else {
        Log.d("error response", "${response.errorBody()}")
      }
    }
  }

  fun addItemToCart(menu:MenuItem){

    val existingItem = _stateFlow.value.plateItems.find { it.id == menu.id }


    if (existingItem != null) {
      existingItem.quantity +=1
    } else {
      val prevPlateItems = _stateFlow.value.plateItems
      val newItem = PlateItem(
        id = menu.id,
        name = menu.name,
        category = menu.category,
        imageUrl = menu.imageUrl,
        rating = menu.rating,
        price = menu.price,
        shortDescription = menu.shortDescription,
        quantity = 1 // Initial quantity for new item
      )
      val newPlateItems = prevPlateItems+newItem

      _uiState.update { currentState: AppState ->
        val newState = currentState.copy(
          plateItems = newPlateItems
        )
        newState
      }
    }
  }

  fun removeItemFromCart(plate: PlateItem) {
    val existingItem = _stateFlow.value.plateItems.find { it.id == plate.id }

    if (existingItem != null) {
      if (existingItem.quantity > 1) {
        existingItem.quantity -= 1
      } else {
        val newPlateItems = _stateFlow.value.plateItems.filterNot { it.id == plate.id }
        _uiState.update { currentState ->
          currentState.copy(
            plateItems = newPlateItems
          )
        }
      }
    }
  }
}