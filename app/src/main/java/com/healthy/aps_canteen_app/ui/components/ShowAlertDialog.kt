package com.healthy.aps_canteen_app.ui.components

import android.app.AlertDialog
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun ShowAlertDialog(message: String, context: Context, onDismiss: () -> Unit, title: String? = null){
  AlertDialog.Builder(context)
    .setTitle(title)
    .setMessage(message)
    .setPositiveButton("DISMISS") { dialog, which ->
      onDismiss()
      dialog.dismiss()
    }
    .setCancelable(false)
    .show()
}