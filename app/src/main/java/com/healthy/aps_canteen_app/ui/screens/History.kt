package com.healthy.aps_canteen_app.ui.screens

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.healthy.aps_canteen_app.ui.theme.DarkGreen
import com.healthy.aps_canteen_app.ui.theme.LightGreen
import com.healthy.aps_canteen_app.ui.viewModel.CustomViewModel
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun History(customViewModel: CustomViewModel, navController: NavHostController, context: Context){
  val appState by customViewModel._stateFlow.collectAsState()

  LaunchedEffect(Unit){
    customViewModel.getOrderHistory(appState.userId)
  }
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(0.9f).fillMaxHeight(0.89f),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier = Modifier.height(20.dp))
      LazyColumn (modifier = Modifier
        .fillMaxWidth()
        ){
        items(appState.orderHistory){history->
          var sum = 0
          Column (
            modifier = Modifier
              .background(LightGreen, RoundedCornerShape(20.dp))
              .clip(RoundedCornerShape(20.dp))
              .padding(10.dp, 20.dp)
             ,){
            history.items.forEach {
              sum +=  it.price.toInt() * it.quantity
              Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Column {
                  Box(
                    modifier = Modifier
                      .size(40.dp)
                      .clip(CircleShape)
                      .background(color = LightGreen, shape = CircleShape)
                  ) {
                    Image(
                      painter = rememberAsyncImagePainter(model = it.imageUrl),
                      contentDescription = null,
                      modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize(),
                      contentScale = ContentScale.Crop,
                    )
                  }
                }
                Column(Modifier.fillMaxWidth(0.7f)) {
                  Text(text = "${it.name} x ${it.quantity} ", fontSize = 18.sp, fontWeight = FontWeight.Bold,color = Color.Black)
                }
              }
              Spacer(modifier = Modifier.height(10.dp))
            }
            Divider(Modifier.padding(0.dp, 5.dp), Color.Gray)
            Row(modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween) {
              Text(text = convertDateFormat(history.timestamp), fontSize = 18.sp, fontWeight = FontWeight.Normal,color = Color.Gray)
              val rupeeSymbol = "\u20B9"
              Text(text ="$rupeeSymbol ${String.format("%.2f", sum.toFloat())}", fontSize = 18.sp, fontWeight = FontWeight.Bold,color = Color.Black)
            }
          }
          Spacer(modifier = Modifier.height(20.dp))

        }
      }
    }
  }
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertDateFormat(inputDate: String): String {
  val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.ENGLISH)
  val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a", Locale.ENGLISH)

  val zonedDateTime = ZonedDateTime.parse(inputDate, inputFormatter)
  val localDateTime = LocalDateTime.from(zonedDateTime)

  // Convert to Indian Standard Time (IST)
  val istOffset = ZoneOffset.ofHoursMinutes(5, 30)
  val istDateTime = zonedDateTime.withZoneSameInstant(istOffset)

  return outputFormatter.format(istDateTime)
}