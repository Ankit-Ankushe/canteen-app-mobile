package com.healthy.aps_canteen_app.ui.components

import androidx.compose.runtime.Composable

data class TabRowItem(
  val title : String,
  val count : String,
  val screen : @Composable () -> Unit
)
