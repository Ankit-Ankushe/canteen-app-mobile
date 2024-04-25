package com.healthy.aps_canteen_app.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.runtime.*
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
import com.healthy.aps_canteen_app.models.PlateItem
import com.healthy.aps_canteen_app.ui.components.EmptyPlate
import com.healthy.aps_canteen_app.ui.theme.DarkGreen
import com.healthy.aps_canteen_app.ui.theme.LightGreen
import com.healthy.aps_canteen_app.ui.viewModel.CustomViewModel

@Composable
fun Plate(customViewModel: CustomViewModel, navController: NavHostController, context: Context){
  val appState by customViewModel._stateFlow.collectAsState()
  var forceRender by remember { mutableStateOf(0) }
  var totalAmount by remember { mutableStateOf(0) }

  var quantity by remember { mutableStateOf("0") }

  fun onAddQuantity(plate: PlateItem) {
    plate.quantity += 1
    // Triggering a rerender
    forceRender++
  }
  fun onMinusQuantity(plate: PlateItem) {
    if(plate.quantity < 2){
      customViewModel.removeItemFromCart(plate)
    }else{
      plate.quantity -= 1
    }
    // Triggering a rerender
    forceRender++
  }
  fun getTotalAmount(){
    var sum = 0
    appState.plateItems.forEach {
      sum += it.quantity * it.price.toInt()
    }
    totalAmount = sum
  }
  LaunchedEffect(forceRender) {
    getTotalAmount()
  }
  fun onProceedToPayClicked(){
    customViewModel.setTotalAmount(totalAmount)
    navController.navigate("payment")
  }
  if(appState.plateItems.isEmpty()){
    EmptyPlate()
  }else{
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.89f)
        .background(Color.White),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Column(modifier = Modifier.fillMaxWidth(0.9f)) {
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn (modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight(0.8f)
        ){
          items(appState.plateItems){plate ->
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
                    painter = rememberAsyncImagePainter(model = plate.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                      .align(Alignment.Center)
                      .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                  )
                }
              }
              Column(Modifier.fillMaxWidth(0.5f)) {
                Text(text = plate.name, fontSize = 18.sp, fontWeight = FontWeight.Bold,color = Color.Black)
                val rupeeSymbol = "\u20B9"
                Text(text = "$rupeeSymbol ${plate.price}",fontSize = 18.sp, fontWeight = FontWeight.Bold,color = Color.Black)
              }
              Column(
                Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Column(
                    modifier = Modifier
                      .padding(5.dp)
                      .clickable { onAddQuantity(plate) }
                  ) {
                    Icon(imageVector = Icons.Default.AddCircle, contentDescription = "", tint = DarkGreen)
                  }
                  Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                    Text(text = plate.quantity.toString(), modifier = Modifier
                      .clip(RoundedCornerShape(5.dp))
                      .width(intrinsicSize = IntrinsicSize.Min)
                      .background(LightGreen, MaterialTheme.shapes.small),
                      fontSize = 16.sp, fontWeight = FontWeight.Bold,color = Color.Black
                    )
                  }
                  Column(
                    modifier = Modifier
                      .padding(5.dp)
                      .clickable { onMinusQuantity(plate) }
                  ) {
                    Icon(imageVector = Icons.Default.RemoveCircle, contentDescription = "", tint = DarkGreen )
                  }
                }
                Spacer(modifier = Modifier.height(10.dp))
                val rupeeSymbol = "\u20B9"
                Text(text = "$rupeeSymbol ${plate.price.toInt() * plate.quantity}",fontSize = 18.sp, fontWeight = FontWeight.Bold,color = Color.Black)
              }
            }
            Spacer(modifier = Modifier.height(20.dp))

          }

        }
      }
      Column(Modifier.fillMaxWidth(0.9f)) {
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
          Text(text = "TOTAL", fontSize = 18.sp, fontWeight = FontWeight.Bold,color = Color.Black)
          val rupeeSymbol = "\u20B9"
          Text(text = "$rupeeSymbol ${String.format("%.2f", totalAmount.toFloat())}", fontSize = 18.sp, fontWeight = FontWeight.Bold,color = Color.Black)
        }
        Divider(Modifier.padding(0.dp, 5.dp), Color.Gray)
        Button(
          onClick = {onProceedToPayClicked() }, modifier = Modifier
            .fillMaxWidth(),
          colors = ButtonDefaults.buttonColors(DarkGreen),
          shape = RoundedCornerShape(15.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(text = "Proceed To Pay", color = Color.White, fontSize = 18.sp)
            Icon(
              imageVector = Icons.Default.ArrowForward,
              contentDescription = "Right Arrow",
              tint = Color.White
            )
          }
        }
      }
    }
  }
}