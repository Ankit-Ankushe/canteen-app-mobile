package com.healthy.aps_canteen_app.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.healthy.aps_canteen_app.ui.screens.*
import com.healthy.aps_canteen_app.ui.theme.DarkGreen
import com.healthy.aps_canteen_app.ui.viewModel.CustomViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
  navController: NavHostController,
  currentRoute: String?,
  context: Context,
  customViewModel: CustomViewModel,
){
  val scaffoldState = rememberScaffoldState()
  val scope = rememberCoroutineScope()
  val appState by customViewModel._stateFlow.collectAsState()

  var appTitle by remember { mutableStateOf("") }

  val items = listOf(
    ApsCanteenNavItems.Menu,
    ApsCanteenNavItems.Plate,
    ApsCanteenNavItems.History
  )
  var navigationIcon = "Menu"

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      Log.d("CurrentRouteToSetAppTile", "$currentRoute")

      if (currentRoute == "menu") {
        appTitle = "Menu"
      }else if (currentRoute == "payment") {
        appTitle = "Payment"
        navigationIcon = "Back"
      }
      AppBar(
        navController = navController,
        navigationIcon = navigationIcon,
        onNavigationIconClick = {
          scope.launch {
            scaffoldState.drawerState.open()
          }
        },
        appTitle
      )
    },
    bottomBar = {
      Log.d("current route", "$currentRoute")
      if (currentRoute == "menu" || currentRoute == "plate"|| currentRoute == "history") {
        BottomNavigation(
          backgroundColor = Color(android.graphics.Color.rgb(22, 36, 48)),
          modifier = Modifier
            .height(75.dp)
        ) {
          val navBarItems: List<NavigationItem> = items

          navBarItems.forEach {
            BottomNavigationItem(selected = currentRoute == it.route,
              modifier = Modifier
                .weight(if (currentRoute == "notification") 1f else 0.7f)
                .background(
                  if (currentRoute == it.route) Color(
                    android.graphics.Color.rgb(
                      33,
                      53,
                      71
                    )
                  ) else Color.Transparent
                )
              ,
              icon = {
                Column(
                  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                      imageVector = it.icons, contentDescription = null,
                      tint = if (currentRoute == it.route) Color.White else Color(
                        android.graphics.Color.rgb(139,146,152)
                      ),
                      modifier = Modifier
                        .background(
                          if (currentRoute == it.route) DarkGreen else Color.Transparent,
                          RoundedCornerShape(10.dp)
                        )
                        .padding(4.dp)
                    )
                  Text(
                    text = it.label,
                    fontSize = 12.sp,
                    fontWeight = if(currentRoute == it.route) FontWeight.Bold else FontWeight.Normal,
                    color = if (currentRoute == it.route) androidx.compose.ui.graphics.Color.White else Color(
                      android.graphics.Color.rgb(139,146,152)
                    ),
                    maxLines = 1, // Set max lines to 1
                    overflow = TextOverflow.Ellipsis, // Add ellipsis for long texts
                    modifier = Modifier.padding(top = 10.dp)
                  )
                }
                if(it.route == "plate"){
                  Badge(modifier = Modifier.padding(30.dp,0.dp,0.dp,0.dp), backgroundColor = Color(android.graphics.Color.rgb(186, 27, 27))){
                    Text(
                      text = appState.plateItems.size.toString(),
                      fontSize = 16.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color.White
                    )
                  }
                }
              },
              onClick = {
                navController.navigate(it.route) {
                  popUpTo(navController.graph.startDestinationId)
                  launchSingleTop = true
                }
                appTitle = it.label.toString()
              }
            )
          }
        }
      } else {
        Column(Modifier.height(0.dp)) {

        }
      }
    }) {
    NavigationController(navController = navController, context, customViewModel)
  }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationController(
  navController: NavHostController,
  context: Context,
  customViewModel: CustomViewModel,
) {

  NavHost(
    navController = navController,
    startDestination =  ApsCanteenNavItems.Menu.route
  ) {

    composable(ApsCanteenRouteItems.Login.route) {
      Login(customViewModel, navController,context)
    }
    composable(ApsCanteenNavItems.Menu.route) {
      Dashboard(customViewModel, navController,context)
    }
    composable(ApsCanteenNavItems.Plate.route) {
      Plate(customViewModel, navController,context)
    }
    composable(ApsCanteenNavItems.History.route) {
      History(customViewModel, navController, context)
    }
    composable(ApsCanteenRouteItems.Payment.route) {
      PaymentScreen(customViewModel, navController, context)
    }
  }
}
