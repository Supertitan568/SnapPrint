package com.example.snapprint.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snapprint.R
import com.example.snapprint.ui.theme.SnapPrintTheme

@Composable
fun LoginScreen(modifier : Modifier = Modifier, showNextScreen : () -> Unit){
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = modifier,
            text = "SNAPPRINT",
            fontSize = 70.sp,
            textAlign = TextAlign.Center
        )

        Row {
            Image(painter = painterResource(id = R.drawable.illustration_of_human_icon_user_symbol_icon_modern_design_on_blank_background_free_vector_1427567401),
                contentDescription = null,
                modifier = Modifier.width(50.dp)
            )

            Text(
                modifier = modifier,
                text = "Sign in",
                fontSize = 25.sp
            )
        }

        var username by remember { mutableStateOf(TextFieldValue()) }
        Column{
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            var password by remember { mutableStateOf(TextFieldValue()) }

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = true
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Button(onClick = showNextScreen) {
                Text("Login")
            }

            Button(onClick = { /* Handle button click */ }) {
                Text("Register")
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview(){

    SnapPrintTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background){
            LoginScreen(modifier = Modifier, {"Do nothing"})
        }
    }
}