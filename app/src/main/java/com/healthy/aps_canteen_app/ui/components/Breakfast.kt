package com.healthy.aps_canteen_app.ui.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.healthy.aps_canteen_app.ui.viewModel.CustomViewModel

@Composable
fun Breakfast(customViewModel: CustomViewModel, navController: NavHostController, context: Context){
  val appState by customViewModel._stateFlow.collectAsState()

  MenuItemsUI(customViewModel, navController, context,appState.breakfastItems)
}