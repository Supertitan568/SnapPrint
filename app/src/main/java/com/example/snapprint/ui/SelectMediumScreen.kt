/*
*  File Name: SelectMediumScreen.kt
*  Author: Lane Odenbach
*  This file allows the user to create an order
* */
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snapprint.UserOrder
import com.example.snapprint.ui.theme.SnapPrintTheme

@Composable
fun SelectMediumScreen(modifier : Modifier = Modifier, createOrder: (newOrder: UserOrder) -> Unit, showNextScreen : () -> Unit, orderSize : Int){
    //This composable lets the user create an order
    //The inputs are functions to add an order to orders and move between the different composables

    val radioOptions = listOf("Small", "Medium", "Large")
    val radioOptions2 = listOf("Mug", "Shirt", "Poster")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[2]) }
    val (selectedOption2, onOptionSelected2) = remember { mutableStateOf(radioOptions2[2]) }
    var newOrder = UserOrder(1, "", "")
    var mediumAmount by remember { mutableStateOf(TextFieldValue()) }


    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = modifier,
            text = "SNAPPRINT",
            fontSize = 70.sp,
            textAlign = TextAlign.Center
        )
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = modifier,
                    text = "Select Category",
                    textAlign = TextAlign.Center
                )
                radioOptions2.forEach { text ->
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
                                selected = (text == selectedOption2),
                                // below method is called on
                                // clicking of radio button.
                                onClick = { onOptionSelected2(text) }
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
                            selected = (text == selectedOption2),
                            modifier = Modifier.padding(all = Dp(value = 8F)),
                            onClick = {
                                // inside on click method we are setting a
                                // selected option of our radio buttons.
                                onOptionSelected2(text)

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


        TextField(
            value = mediumAmount,
            onValueChange = { mediumAmount = it },
            label = { Text("Category Amount") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )


        Button(onClick = {
            createOrder(UserOrder(orderSize, mediumAmount.text, selectedOption2))
            showNextScreen()
        }
        ) {
            Text("Next")
        }
    }
}

@Preview
@Composable
fun SelectMediumPreview(){
    //Displays the above composable
    SnapPrintTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background){
            SelectMediumScreen(modifier = Modifier, fun(newOrder: UserOrder){"nothing"}, {"do nothing"}, 1)
        }
    }
}