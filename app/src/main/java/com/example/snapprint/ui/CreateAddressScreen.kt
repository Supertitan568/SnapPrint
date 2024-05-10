/*
*  File Name: CreateAddressScreen.kt
*  Author: Lane Odenbach
*  This file lets add a new address to deliver to
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
import com.example.snapprint.ui.theme.SnapPrintTheme

@Composable
fun CreateAddressScreen(modifier: Modifier, showNextScreen : () -> Unit, addCreatedAddress : (createdAddress : Address ) -> Boolean){
    // This composable lets add a new address to deliver to

    var street1 by remember { mutableStateOf(TextFieldValue()) }
    var street2 by remember { mutableStateOf(TextFieldValue()) }
    var city by remember { mutableStateOf(TextFieldValue()) }
    var state by remember { mutableStateOf(TextFieldValue()) }
    var zip by remember { mutableStateOf(TextFieldValue()) }
    var name by remember { mutableStateOf(TextFieldValue()) }
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = street1,
            onValueChange = { street1 = it },
            label = { Text("Street1") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )
        TextField(
            value = street2,
            onValueChange = { street2 = it },
            label = { Text("Street2") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("City") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )
        TextField(
            value = state,
            onValueChange = { state = it },
            label = { Text("State") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )
        TextField(
            value = zip,
            onValueChange = { zip = it },
            label = { Text("Zip Code") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )

        //Brings the user to the next screen if not blank
        Button(onClick = {
            if(addCreatedAddress(Address(street1.text, street2.text, city.text, state.text, zip.text, name.text))){
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
fun AddressCreationPreview(){
    //This displays the above composable

    SnapPrintTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background){
            CreateAddressScreen(modifier = Modifier, {"do nothing"}, {createdAddress : Address -> true })
        }
    }
}