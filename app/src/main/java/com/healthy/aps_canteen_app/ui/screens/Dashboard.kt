package com.healthy.aps_canteen_app.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.healthy.aps_canteen_app.ui.components.ShowLoader
import com.healthy.aps_canteen_app.ui.components.TabbedScreen
import com.healthy.aps_canteen_app.ui.viewModel.CustomViewModel
import com.healthy.aps_canteen_app.ui.viewModel.TabViewModel

@Composable
fun Dashboard(
  customViewModel: CustomViewModel,
  navController: NavHostController,
  context: Context){
  val tabViewModel = viewModel<TabViewModel>() // Creating TabViewModel instance
  val appState by customViewModel._stateFlow.collectAsState()

  LaunchedEffect(Unit) {
    customViewModel.fetchMenuItems()
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {

    if (appState.showLoader) {
      ShowLoader()
    }else{
      Column(modifier = Modifier.fillMaxWidth(0.9f)) {
        Text(text = "Eat Healthy", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Color(0, 77, 0))
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Our Daily Healthy Meal Plans", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
      }
      Spacer(modifier = Modifier.height(20.dp))
      TabbedScreen(customViewModel, navController, context, tabViewModel) // Pass TabViewModel to TabbedScreen
    }
    }
  }