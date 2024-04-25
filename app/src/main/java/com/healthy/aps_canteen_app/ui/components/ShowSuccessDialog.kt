package com.healthy.aps_canteen_app.ui.components

import android.app.AlertDialog
import android.content.Context
import androidx.compose.runtime.Composable

@Composable
fun ShowSuccessDialog(message: String, context: Context, onDismiss: () -> Unit, title: String? = null){

  AlertDialog.Builder(context)
    .setTitle("SUCCESS")
    .setMessage(message)
    .setPositiveButton("OK") { dialog, which ->
      onDismiss()
      dialog.dismiss()
    }
    .setCancelable(false)
    .show()
}