package com.healthy.aps_canteen_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.healthy.aps_canteen_app.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun EmptyPlate(){
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .background(Color.White)
      .fillMaxHeight(0.89f),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        painter = painterResource(id = R.drawable.empty_logo),
        contentDescription = null,
        contentScale = ContentScale.Crop,
      )
      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = "Food Not Found",
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        )

    }
  }
}