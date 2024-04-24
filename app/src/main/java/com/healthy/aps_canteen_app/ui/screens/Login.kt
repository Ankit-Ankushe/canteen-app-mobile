package com.healthy.aps_canteen_app.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.healthy.aps_canteen_app.R
import com.healthy.aps_canteen_app.ui.components.ShowAlertDialog
import com.healthy.aps_canteen_app.ui.theme.DarkGreen
import com.healthy.aps_canteen_app.ui.theme.LightGreen
import com.healthy.aps_canteen_app.ui.viewModel.CustomViewModel

@Composable
fun Login(customViewModel: CustomViewModel, navController: NavHostController, context: Context){
  var userName by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var passwordVisibility by remember { mutableStateOf(false) }
  var showPassword by remember { mutableStateOf(false) }
  var showDialog by remember { mutableStateOf(false) }
  var (alertMessage,setAlertMessage) = remember() { mutableStateOf("") }


  fun onLoginClicked(){
    customViewModel.validateUser(userName,password){
      if(it){
        navController.navigate("menu")
      }else {
        showDialog = true
        setAlertMessage("Invalid username or password")
      }
    }
  }

  if (showDialog) {
    ShowAlertDialog(alertMessage, context, onDismiss = { showDialog = false },title = "ALERT")
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(LightGreen),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    Spacer(modifier = Modifier.height(40.dp))

    Column(
      modifier = Modifier
    ) {
      Box(
        modifier = Modifier
          .size(200.dp)
          .background(color = Color.White, shape = RoundedCornerShape(50.dp))
      ) {
        Image(
          painter = painterResource(id = R.drawable.apsdc_logo),
          contentDescription = null,
          modifier = Modifier.align(Alignment.Center)
            .align(Alignment.Center)
            .fillMaxSize(0.8f),
          contentScale = ContentScale.Fit,
        )
      }
    }
    Spacer(modifier = Modifier.height(40.dp))

    Column(
      modifier = Modifier.padding(20.dp, 0.dp)
    ) {
      Text(text = "Username", color = Color.Black)
      Spacer(modifier = Modifier.height(10.dp))
      BasicTextField(
        modifier = Modifier
          .clip(RoundedCornerShape(10.dp))
          .fillMaxWidth()
          .height(45.dp)
          .border(1.dp, Color.White, RoundedCornerShape(10.dp))
          .background(Color.White, RoundedCornerShape(10.dp))
          .padding(10.dp),
        value = userName,
        maxLines = 1,
        onValueChange = { userName = it },
        singleLine = false,
        cursorBrush = SolidColor(
          Color.Black
        ),
        textStyle = LocalTextStyle.current.copy(color = Color.Black, fontSize = 18.sp,),
      )
    }
    Spacer(modifier = Modifier.height(20.dp))
    Column(
      modifier = Modifier.padding(20.dp, 0.dp)
    ) {
      Text(text = "Password", color = Color.Black)
      Spacer(modifier = Modifier.height(10.dp))
      Box {
        BasicTextField(
          value = password,
          onValueChange = { password = it },
          modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(40.dp)
            .border(1.dp, Color.White, RoundedCornerShape(10.dp))
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(10.dp),
          maxLines = 1,
          cursorBrush = SolidColor(Color.Black),
          textStyle = LocalTextStyle.current.copy(color = Color.Black, fontSize = 18.sp),
          visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
        )
        Box(
          contentAlignment = Alignment.CenterEnd
        ) {
          BasicTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(Color.White, RoundedCornerShape(10.dp)).padding(10.dp),
            maxLines = 1,
            cursorBrush = SolidColor(Color.Black),
            textStyle = LocalTextStyle.current.copy(color = Color.Black, fontSize = 18.sp),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
          )
          Box(
            modifier = Modifier,
            contentAlignment = Alignment.CenterEnd
          ){
            IconButton(onClick = { showPassword = !showPassword }) {
              Icon(
                imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                contentDescription = if (showPassword) "Hide password" else "Show password",
                tint = Color.Black
              )
            }
          }
        }
      }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Column(modifier = Modifier.padding(20.dp, 0.dp)) {
      Button(
        onClick = { onLoginClicked() }, modifier = Modifier
          .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(DarkGreen),
        shape = RoundedCornerShape(15.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(text = "Login", color = Color.White, fontSize = 18.sp)
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