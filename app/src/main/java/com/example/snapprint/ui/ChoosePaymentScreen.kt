/*
*  File Name: ChoosePaymentScreen.kt
*  Author: Lane Odenbach
*  This file contains the composable that chooses the payment method
* */
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snapprint.PaymentMethod
import com.example.snapprint.ui.theme.SnapPrintTheme


@Composable
fun ChoosePaymentScreen(modifier : Modifier = Modifier, payments : MutableList<PaymentMethod>, showNextScreen : () -> Unit, showPaymentCreationScreen: () -> Unit){
    //This displays the screen that shows all of the payment methods
    //Inputs are the functions used to move between the different composables

    val radioOptions = mutableListOf<String>()

    //Populates RadioOptions with the different payment methods
    for(payment in payments){
        radioOptions.add(payment.type)
    }

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[2]) }

    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            modifier = modifier,
            text = "SNAPPRINT",
            fontSize = 70.sp,
            textAlign = TextAlign.Center
        )
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                modifier = modifier,
                text = "Select Payment Type",
                textAlign = TextAlign.Center
            )
            //This creates buttons for each payment method
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        // using modifier to add max
                        // width to our radio button.
                        .fillMaxWidth()
                        // below method is use to add
                        // selectable to our radio button.
                        .selectable(
                            // this method is called when
                            // radio button is selected.
                            selected = (text == selectedOption),
                            // below method is called on
                            // clicking of radio button.
                            onClick = { onOptionSelected(text) }
                        )
                        // below line is use to add
                        // padding to radio button.
                        .padding(horizontal = 16.dp)
                ) {
                    // below line is use to get context.
                    val context = LocalContext.current

                    // below line is use to
                    // generate radio button
                    RadioButton(
                        // inside this method we are
                        // adding selected with a option.
                        selected = (text == selectedOption),modifier = Modifier.padding(all = Dp(value = 8F)),
                        onClick = {
                            // inside on click method we are setting a
                            // selected option of our radio buttons.
                            onOptionSelected(text)

                            // after clicking a radio button
                            // we are displaying a toast message.
                            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                        }
                    )
                    // below line is use to add
                    // text to our radio buttons.
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }

        }
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

            Button(onClick = showPaymentCreationScreen) {
                Text("Add Payment Type")
            }
            Button(onClick = showNextScreen) {
                Text("Next")
            }
        }
    }

}

@Preview
@Composable
fun ChoosePaymentScreenPreview(){
    //Used to Preview the composable above
    var paymentMethods = mutableListOf(
        PaymentMethod("123456789", "Lane Odenbach", "1/1/1970", "420", "Apple Pay"),
        PaymentMethod("987654321", "Liam Meyer", "3/14/15", "696", "Google Pay"),
        PaymentMethod("420694206", "Caden Graf", "5/4/24", "121", "Credit Card")
    )
    SnapPrintTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background){
            ChoosePaymentScreen(modifier = Modifier, paymentMethods, {"Do nothing"}, {})
        }
    }
}