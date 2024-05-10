/*
*  File Name: MainActivity.kt
*  Author: Lane Odenbach
*  This file contains the main program logic
*  It also defines the classes and lists used to contain the program data
* */
package com.example.snapprint

import ChoosePaymentScreen
import ChoosePickupScreen
import HomeScreen
import SelectMediumScreen
import ThankYouScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.tooling.preview.Preview
import com.example.snapprint.ui.CreateAddressScreen
import com.example.snapprint.ui.CreatePaymentMethodScreen
import com.example.snapprint.ui.LoginScreen
import com.example.snapprint.ui.theme.SnapPrintTheme


class UserOrder(val oN : Int, val dO : String, val oC : String){
    var orderNum = 0
    var amount = ""
    var orderCategory = ""

    init {
        this.orderNum = oN
        this.amount = dO
        this.orderCategory = oC
    }
}

class Address(val s1 : String, val s2 : String, val c : String, val st : String, val z : String, val n : String){
    var street1 = ""
    var street2 = ""
    var city = ""
    var state = ""
    var zipCode = ""
    var name = ""

    init{
        this.street1 = s1
        this.street2 = s2
        this.city = c
        this.state = st
        this.zipCode = z
        this.name  = n
    }

}

class Account(val uname : String, val pword : String){
    var username = ""
    var password = ""

    init {
        this.username = uname
        this.password = pword
    }
}

class PaymentMethod(val cnum : String, val chName : String, val exDate : String, val cCode : String, val t : String){
    var card_num = ""
    var cardHolderName = ""
    var expirationDate = ""
    var cvcCode = ""
    var type = ""
    init {
        this.card_num = cnum
        this.cardHolderName = chName
        this.expirationDate = exDate
        this.cvcCode = cCode
        this.type = t
    }
}

class MainActivity : ComponentActivity() {
    //This just runs the function below
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnapPrintTheme {
                SnapPrintApp()
            }
        }
    }
}


@Composable
fun SnapPrintApp(modifier: Modifier = Modifier){
    //This is the main function of the program. It contains the logic for moving between the different composables

    //This defines the lists where the program data is stored
    var onNextClicked by remember { mutableIntStateOf(0) }
    var orders = mutableListOf(UserOrder(1, "5", "Shirt"), UserOrder(2, "5", "Mug"), UserOrder(3, "8", "Shirt"))
    var addresses = mutableListOf(
        Address("308 Negra Arroya Lane", "", "Albuquerque", "New Mexico", "87193", "Home"),
        Address("1317 4th Avenue NE", "", "Jamestown", "North Dakota", "58401", "College")
    )
    var accounts = mutableListOf(Account("lane.odenbach", "test123"), Account("caden.graf", "test123"))

    var paymentMethods = mutableListOf(
        PaymentMethod("123456789", "Lane Odenbach", "1/1/1970", "420", "Apple Pay"),
        PaymentMethod("987654321", "Liam Meyer", "3/14/15", "696", "Google Pay"),
        PaymentMethod("420694206", "Caden Graf", "5/4/24", "121", "Credit Card")
    )

    //This selects and calls the different composables
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        if(onNextClicked == 0){
            LoginScreen(modifier.background(color = Color.Cyan), showNextScreen = {onNextClicked += 1}, { uname: String, pword: String ->
                    var isVerified = false
                    for (account in accounts) {
                        if(account.username == uname && account.password == pword){
                            isVerified = true
                        }
                    }
                    isVerified
                })
        }
        else if (onNextClicked == 1){
            HomeScreen(modifier.background(color = Color.Cyan), orders, showNextScreen = {onNextClicked += 1}, showLoginScreen = {onNextClicked -= 1})
        }
        else if (onNextClicked == 2){
            SelectMediumScreen(modifier.background(color =  Color.Cyan), createOrder = fun(newOrder: UserOrder ) {orders.add(newOrder)}, showNextScreen = {onNextClicked += 1}, orderSize = orders.size + 1)
        }
        else if (onNextClicked == 3){
            ChoosePickupScreen(modifier.background(color = Color.Cyan), showNextScreen = {onNextClicked += 2}, addresses = addresses, showAdressCreationScreen = {onNextClicked +=1})
        }
        else if (onNextClicked == 4){
            CreateAddressScreen(modifier.background(color = Color.Cyan), showNextScreen = {onNextClicked += 1}, {createdAddress : Address ->
                var notBlank = false
                if(createdAddress.street1 != "" && createdAddress.street2 != "" && createdAddress.city != "" && createdAddress.state != "" && createdAddress.zipCode != ""  && createdAddress.name != ""){
                    addresses.add(createdAddress)
                    notBlank = true
                }
                notBlank
            })
        }
        else if (onNextClicked == 5){
            ChoosePaymentScreen(modifier.background(color = Color.Cyan), paymentMethods, showNextScreen = {onNextClicked += 2}, showPaymentCreationScreen = {onNextClicked += 1})
        }
        else if(onNextClicked == 6){
            CreatePaymentMethodScreen(modifier = Modifier, showNextScreen = {onNextClicked -= 1}, addCreatedPayment = { createdPayment: PaymentMethod ->
                var notBlank = false
                if (createdPayment.card_num != "" && createdPayment.cardHolderName != "" && createdPayment.expirationDate != "" && createdPayment.cvcCode != "" && createdPayment.type != "") {
                    paymentMethods.add(createdPayment)
                    notBlank = true
                }
                notBlank
            })
        }
        else if (onNextClicked == 7){
            ThankYouScreen(modifier.background(color = Color.Cyan), showNextScreen = {onNextClicked = 1})
        }
    }
}