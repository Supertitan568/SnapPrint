/*
*  File Name: ChoosePickupScreen.kt
*  Author: Lane Odenbach
*  This file lets the user choose a pickup location
*
* */
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
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
import com.example.snapprint.Address
import com.example.snapprint.ui.theme.SnapPrintTheme

@Composable
fun ChoosePickupScreen(modifier : Modifier = Modifier, addresses : MutableList<Address>, showNextScreen : () -> Unit, showAdressCreationScreen : () -> Unit){
    //This function lets the user choose a pickup location

    //Populates radioOptions with the names from the different addresses in addresses
    val radioOptions = mutableListOf<String>()
    for(addy in addresses){
        radioOptions.add(addy.name)
    }

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
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
                text = "Select Pickup Location",
                textAlign = TextAlign.Center
            )
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
                        selected = (text == selectedOption),
                        modifier = Modifier.padding(all = Dp(value = 8F)),
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

            Button(onClick = showAdressCreationScreen) {
                Text("Tap to add address")
            }
            Button(onClick = showNextScreen) {
                Text("Next")
            }
        }
    }

}

@Preview
@Composable
fun ChoosePickupScreenPreview(){
    //This composable displays the above screen
    SnapPrintTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background){
            ChoosePickupScreen(modifier = Modifier, mutableListOf(Address("",""," ", " ", " ","whatever"), ),  {"do nothing"}, fun(){"do nothing"})
        }
    }
}