package com.healthy.aps_canteen_app.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TabViewModel : ViewModel() {
  val selectedTabIndex = mutableStateOf(0)

  fun setSelectedTabIndex(index: Int) {
    selectedTabIndex.value = index
  }
}