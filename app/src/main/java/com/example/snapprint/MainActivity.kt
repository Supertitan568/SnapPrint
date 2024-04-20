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

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        if(onNextClicked == 0){
            LoginScreen(modifier, showNextScreen = {onNextClicked += 1})
        }
        else if (onNextClicked == 1){
            HomeScreen(modifier, showNextScreen = {onNextClicked += 1})
        }
        else if (onNextClicked == 2){
            SelectMediumScreen(modifier, showNextScreen = {onNextClicked += 1})
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