/*
*  File Name: CreatePaymentMethodScreen.kt
*  Author: Lane Odenbach
*  This file creates the payment method
* */
package com.example.snapprint.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.snapprint.Address
import com.example.snapprint.PaymentMethod
import com.example.snapprint.ui.theme.SnapPrintTheme

@Composable
fun CreatePaymentMethodScreen(modifier: Modifier, showNextScreen : () -> Unit, addCreatedPayment : (createdPayment: PaymentMethod) -> Boolean){
    // This composable gathers the information for all of the composables

    var cardNumber by remember { mutableStateOf(TextFieldValue()) }
    var cardHolderName by remember { mutableStateOf(TextFieldValue()) }
    var expirationDate by remember { mutableStateOf(TextFieldValue()) }
    var cvcCode by remember { mutableStateOf(TextFieldValue()) }
    var type by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = cardNumber,
            onValueChange = { cardNumber = it },
            label = { Text("card number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )
        TextField(
            value = cardHolderName,
            onValueChange = { cardHolderName = it },
            label = { Text("card holder name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )
        TextField(
            value = expirationDate,
            onValueChange = { expirationDate = it },
            label = { Text("expiration date") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )
        TextField(
            value = cvcCode,
            onValueChange = { cvcCode = it },
            label = { Text("cvc") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )
        TextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("type") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )

        //if above text fields are blank it will stay on the screen
        Button(onClick = {
            if(addCreatedPayment(PaymentMethod(cardNumber.text, cardHolderName.text, expirationDate.text, cvcCode.text, type.text))){
                showNextScreen()
            }
        }
        ) {
            Text(text = "Create Address")

        }
    }
}

@Preview
@Composable
fun PaymentCreationPreview(){
    // This shows the composable above
    SnapPrintTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background){
            CreatePaymentMethodScreen(modifier = Modifier , showNextScreen = { /*TODO*/ }, addCreatedPayment = {createdPayment : PaymentMethod -> true})


        }
    }
}