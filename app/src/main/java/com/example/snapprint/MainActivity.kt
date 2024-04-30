package com.example.snapprint

import ChoosePaymentScreen
import ChoosePickupScreen
import HomeScreen
import SelectMediumScreen
import ThankYouScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.snapprint.ui.LoginScreen
import com.example.snapprint.ui.theme.SnapPrintTheme


class UserOrder(val oN : Int, val dO : String, val oC : String){
    var orderNum = 0
    var dateOrdered = ""
    var orderCategory = ""

    init {
        this.orderNum = oN
        this.dateOrdered = dO
        this.orderCategory = oC
    }
}
class MainActivity : ComponentActivity() {
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
    var onNextClicked by remember { mutableIntStateOf(0) }
    var orders = mutableListOf(UserOrder(1, "1/1/1970", "Shirt"), UserOrder(2, "9/11/2001", "Mug"), UserOrder(1, "8/12/2003", "Shirt"))

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        if(onNextClicked == 0){
            LoginScreen(modifier, showNextScreen = {onNextClicked += 1})
        }
        else if (onNextClicked == 1){
            HomeScreen(modifier, orders, showNextScreen = {onNextClicked += 1}, showLoginScreen = {onNextClicked += 1})
        }
        else if (onNextClicked == 2){
            SelectMediumScreen(modifier, createOrder = fun(newOrder: UserOrder ) {orders.add(newOrder)}, showNextScreen = {onNextClicked += 1}, orderSize = orders.size)
        }
        else if (onNextClicked == 3){
            ChoosePickupScreen(modifier, showNextScreen = {onNextClicked += 1})
        }
        else if (onNextClicked == 4){
            ChoosePaymentScreen(modifier, showNextScreen = {onNextClicked += 1})
        }
        else if (onNextClicked == 5){
            ThankYouScreen(modifier, showNextScreen = {onNextClicked = 1})
        }
    }
}