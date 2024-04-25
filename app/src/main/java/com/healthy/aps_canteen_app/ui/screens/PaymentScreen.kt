package com.healthy.aps_canteen_app.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.healthy.aps_canteen_app.ui.components.ShowAlertDialog
import com.healthy.aps_canteen_app.ui.components.ShowLoader
import com.healthy.aps_canteen_app.ui.components.ShowSuccessDialog
import com.healthy.aps_canteen_app.ui.theme.DarkGreen
import com.healthy.aps_canteen_app.ui.viewModel.CustomViewModel
import kotlin.math.exp

@Composable
fun PaymentScreen(customViewModel: CustomViewModel, navController: NavHostController, context: Context){
  val appState by customViewModel._stateFlow.collectAsState()

  var cardNumber by remember { mutableStateOf("") }
  var expireDate by remember { mutableStateOf("") }
  var cvv by remember { mutableStateOf("") }
  var name by remember { mutableStateOf("") }
  var showSuccessDialog by remember { mutableStateOf(false) }
  var showLoader by remember { mutableStateOf(false) }

  var showAlertDialog by remember { mutableStateOf(false) }
  var alertMessage by remember { mutableStateOf("") }
  fun onPayClicked(){
    if(cardNumber.length != 16){
      if(cardNumber.isEmpty()){
        alertMessage  = "Fill Card Number"
      }else{
        alertMessage  = "Wrong Card Number"
      }
      showAlertDialog = true
    }else if(expireDate.length != 7){
      if(expireDate.isEmpty()){
        alertMessage  = "Fill Expire Date"
      }else{
        alertMessage  = "Wrong Expire Date"
      }
      showAlertDialog = true
    }else if(cvv.length != 3){
      if(cvv.isEmpty()){
        alertMessage  = "Fill  CVV"
      }else{
        alertMessage  = "Wrong CVV"
      }
      showAlertDialog = true
    }else if(name.isEmpty()){
      alertMessage  = "Fill Name"
      showAlertDialog = true
    }else{
      showLoader = true
      customViewModel.makePlateEmpty()
      customViewModel.placeOrder(appState.userId,appState.plateItems){
        if(it){
          showLoader= false
          showSuccessDialog = true
          navController.navigate("menu")
        }
      }
    }

  }
  if(showAlertDialog){
    ShowAlertDialog(message = alertMessage, context = context, onDismiss = { showAlertDialog = false})
  }
  if(showSuccessDialog){
    ShowSuccessDialog(message = "Order placed successfully!", context = context, onDismiss = { showSuccessDialog = false})
  }
  if(showLoader){
    ShowLoader()
  }else{
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Column(
        modifier = Modifier
          .fillMaxHeight(0.89f)
          .fillMaxWidth(0.9f),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Column(
          modifier = Modifier
        ) {
          Text(text = "Card Number", color = Color.Black)
          Spacer(modifier = Modifier.height(10.dp))
          BasicTextField(
            modifier = Modifier
              .clip(RoundedCornerShape(10.dp))
              .fillMaxWidth()
              .height(45.dp)
              .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
              .background(Color.White, RoundedCornerShape(10.dp))
              .padding(10.dp),
            value = cardNumber,
            maxLines = 1,
            onValueChange = { cardNumber = it },
            singleLine = false,
            cursorBrush = SolidColor(
              Color.Black
            ),
            textStyle = LocalTextStyle.current.copy(color = Color.Black, fontSize = 18.sp,),
          )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
          modifier = Modifier,
          horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
          Column(
            modifier = Modifier.weight(0.5f)
          ) {
            Text(text = "Expire Date", color = Color.Black)
            Spacer(modifier = Modifier.height(10.dp))
            BasicTextField(
              modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(45.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(10.dp),
              value = expireDate,
              maxLines = 1,
              onValueChange = { expireDate = it },
              singleLine = false,
              cursorBrush = SolidColor(
                Color.Black
              ),
              textStyle = LocalTextStyle.current.copy(color = Color.Black, fontSize = 18.sp,),
            )
          }
          Column(
            modifier = Modifier.weight(0.5f)
          ) {
            Text(text = "CVV", color = Color.Black)
            Spacer(modifier = Modifier.height(10.dp))
            BasicTextField(
              modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(45.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(10.dp),
              value = cvv,
              maxLines = 1,
              onValueChange = { cvv = it },
              singleLine = false,
              cursorBrush = SolidColor(
                Color.Black
              ),
              textStyle = LocalTextStyle.current.copy(color = Color.Black, fontSize = 18.sp,),
            )
          }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
          modifier = Modifier.weight(0.5f)
        ) {
          Text(text = "Name", color = Color.Black)
          Spacer(modifier = Modifier.height(10.dp))
          BasicTextField(
            modifier = Modifier
              .clip(RoundedCornerShape(10.dp))
              .fillMaxWidth()
              .height(45.dp)
              .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
              .background(Color.White, RoundedCornerShape(10.dp))
              .padding(10.dp),
            value = name,
            maxLines = 1,
            onValueChange = { name = it },
            singleLine = false,
            cursorBrush = SolidColor(
              Color.Black
            ),
            textStyle = LocalTextStyle.current.copy(color = Color.Black, fontSize = 18.sp,),
          )
        }

        Column(Modifier.fillMaxWidth(0.9f)) {
          Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "TOTAL", fontSize = 18.sp, fontWeight = FontWeight.Bold,color = Color.Black)
            val rupeeSymbol = "\u20B9"
            Text(text = "$rupeeSymbol ${String.format("%.2f", appState.totalAmount.toFloat())}", fontSize = 18.sp, fontWeight = FontWeight.Bold,color = Color.Black)
          }
          Divider(Modifier.padding(0.dp, 5.dp), Color.Gray)
          Button(
            onClick = { onPayClicked() }, modifier = Modifier
              .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(DarkGreen),
            shape = RoundedCornerShape(15.dp)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically
            ) {
              val rupeeSymbol = "\u20B9"
              Text(text = "Pay $rupeeSymbol ${String.format("%.2f", appState.totalAmount.toFloat())}", color = Color.White, fontSize = 18.sp)
            }
          }
        }
      }
    }
  }
}