package com.healthy.aps_canteen_app.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddBox
import androidx.compose.runtime.Composable
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
import com.healthy.aps_canteen_app.models.apiResponse.MenuItem
import com.healthy.aps_canteen_app.ui.theme.DarkGreen
import com.healthy.aps_canteen_app.ui.theme.LightGreen
import com.healthy.aps_canteen_app.ui.viewModel.CustomViewModel

@Composable
fun MenuItemsComponent(customViewModel: CustomViewModel, navController: NavHostController, context: Context,menuList:List<MenuItem>){
  val appState by customViewModel._stateFlow.collectAsState()

  fun onAddItemClicked(menu: MenuItem) {
    customViewModel.addItemToCart(menu)
    Toast.makeText(context, "Food added to plate!", Toast.LENGTH_SHORT).show()
  }
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight(0.89f)
      .background(Color.White),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Column(modifier = Modifier.fillMaxWidth(0.9f)) {
      LazyColumn (modifier = Modifier
        .fillMaxWidth()){
        items(menuList){menu->
          Row(modifier = Modifier
            .fillMaxWidth()
            .background(LightGreen, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .padding(10.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column {
              Box(
                modifier = Modifier
                  .size(80.dp)
                  .clip(CircleShape)
                  .background(color = LightGreen, shape = CircleShape)
              ) {

                Image(
                  painter = rememberAsyncImagePainter(model = menu.imageUrl),
                  contentDescription = null,
                  modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize(),
                  contentScale = ContentScale.Crop,
                )
              }
            }
            Column(Modifier.fillMaxWidth(0.7f)) {
              Text(text = menu.name, fontSize = 18.sp, fontWeight = FontWeight.Bold,color = Color.Black)
              Text(text = menu.shortDescription,fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
              val rupeeSymbol = "\u20B9"
              Text(text = "$rupeeSymbol ${menu.price}",fontSize = 18.sp, fontWeight = FontWeight.Bold,color = Color.Black)
            }
            Column(
              Modifier
                .align(Alignment.Bottom)
                .clickable {onAddItemClicked(menu)}
            ) {
              Icon(imageVector = Icons.Rounded.AddBox, contentDescription = "Right Arrow", tint = DarkGreen, modifier = Modifier.size(45.dp))
            }
          }
          Spacer(modifier = Modifier.height(20.dp))
        }

      }
    }
  }
}