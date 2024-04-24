package com.healthy.aps_canteen_app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.healthy.aps_canteen_app.ui.theme.DarkGreen

@Composable
fun ShowLoader() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight(0.89f),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      CircularProgressIndicator(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        color = DarkGreen
      )

    }
  }
}