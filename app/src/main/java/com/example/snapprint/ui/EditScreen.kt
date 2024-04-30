package com.example.snapprint.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.snapprint.ui.theme.SnapPrintTheme

@Composable
fun EditScreen(modifier: Modifier, showNextScreen : () -> Unit){
    val sizeOptions = listOf("Small", "Medium", "Large")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(sizeOptions[2]) }
    var mediumAmount by remember { mutableStateOf(TextFieldValue()) }


    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            modifier = modifier,
            text = "Select Mediums",
            textAlign = TextAlign.Center
        )
        TextField(
            value = mediumAmount,
            onValueChange = { mediumAmount = it },
            label = { Text("Select amount") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true
        )

        sizeOptions.forEach { text ->
            Row(
                androidx.compose.ui.Modifier
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
                    modifier = androidx.compose.ui.Modifier.padding(all = Dp(value = 8F)),
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
                    modifier = androidx.compose.ui.Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectMediumPreview(){

    SnapPrintTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background){
            EditScreen(modifier = Modifier, {"do nothing"})
        }
    }
}