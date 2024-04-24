package com.healthy.aps_canteen_app.ui.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.healthy.aps_canteen_app.ui.screens.MenuItemsComponent
import com.healthy.aps_canteen_app.ui.viewModel.CustomViewModel

@Composable
fun Lunch(customViewModel: CustomViewModel, navController: NavHostController, context: Context){
  val appState by customViewModel._stateFlow.collectAsState()

  MenuItemsComponent(customViewModel, navController, context,appState.lunchItems)
}