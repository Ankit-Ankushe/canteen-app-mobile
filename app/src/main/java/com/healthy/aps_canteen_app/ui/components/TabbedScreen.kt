package com.healthy.aps_canteen_app.ui.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.healthy.aps_canteen_app.ui.screens.Breakfast
import com.healthy.aps_canteen_app.ui.viewModel.CustomViewModel
import com.healthy.aps_canteen_app.ui.viewModel.TabViewModel

@Composable
fun TabbedScreen(
  customViewModel: CustomViewModel,
  navController: NavHostController,
  context: Context,
  tabViewModel: TabViewModel

) {
  val appState by customViewModel._stateFlow.collectAsState()
  val selectedTabIndex by tabViewModel.selectedTabIndex

  val tabRowItems = listOf(
    TabRowItem(title = "All".toUpperCase(), count = appState.menuItemsCount.toString(), screen = { AllMenuItems(customViewModel = customViewModel, navController = navController, context = context) }),
    TabRowItem(title = "Breakfast".toUpperCase(), count = appState.breakfastCount.toString(), screen = { Breakfast(customViewModel = customViewModel, navController = navController, context = context) }),
    TabRowItem(title = "Lunch".toUpperCase(), count = appState.lunchCount.toString(), screen = { Lunch(customViewModel = customViewModel, navController = navController, context = context) }),
    TabRowItem(title = "Beverages".toUpperCase(), count = appState.beveragesCount.toString(), screen = { Beverages(customViewModel = customViewModel, navController = navController, context = context) }),
    TabRowItem(title = "Dessert".toUpperCase(), count = appState.dessertCount.toString(), screen = { Dessert(customViewModel = customViewModel, navController = navController, context = context) })
  )
  val tabs = tabRowItems

  Column(modifier = Modifier.fillMaxSize(),
  horizontalAlignment = Alignment.CenterHorizontally) {
    // Tabs
    Row(modifier = Modifier.fillMaxWidth(0.9f),
      horizontalArrangement = Arrangement.Center
    ) {
      ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        contentColor = Color.Black,
        backgroundColor = Color.Transparent,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
          TabRowDefaults.Indicator(Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]))
        },
      ) {
        tabs.forEachIndexed { index, (title,count, icon) ->
          Tab(
            text = {
              Row() {
                Text(title, color = if (selectedTabIndex == index) Color.Black else Color.Gray)
                Spacer(modifier = Modifier.width(10.dp))
                Badge(backgroundColor = Color(android.graphics.Color.rgb(186, 27, 27))) {
                  Text(
                    text = count,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                  )
                }
              }
            },
            selected = selectedTabIndex == index,
            onClick = {
              tabViewModel.setSelectedTabIndex(index) // Update selected tab index in TabViewModel
            },
          )
        }
      }
    }
    Spacer(modifier = Modifier.height(10.dp))

    Column(modifier = Modifier
      .fillMaxWidth()) {

      // Content based on selected tab
      when (selectedTabIndex) {
        0 -> AllMenuItems(customViewModel, navController, context)
        1 -> Breakfast(customViewModel, navController, context)
        2 -> Lunch(customViewModel, navController, context)
        3 -> Beverages(customViewModel, navController, context)
        4 -> Dessert(customViewModel, navController, context)
      }
    }
  }
}
