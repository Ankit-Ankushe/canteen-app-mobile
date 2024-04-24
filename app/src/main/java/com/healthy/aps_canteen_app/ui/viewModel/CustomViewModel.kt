package com.healthy.aps_canteen_app.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.healthy.aps_canteen_app.data.ApiInterfaceFactory
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

}