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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snapprint.ui.theme.SnapPrintTheme

@Composable
fun TableScreen2() {
    // Just a fake data... a Pair of Int and String
    // Each cell of a column must have the same weight.
    val column1Weight = .3f // 30%
    val column2Weight = .3f // 70%
    val column3Weight = .3f // 70%


    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(
        Modifier
            .padding(16.dp)) {
        // Here is the header
        item {
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Shirt", weight = column1Weight)
                TableCell(text = "M", weight = column2Weight)
                TableCell(text = "2", weight = column3Weight)

            }
        }

        item {
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Blanket", weight = column1Weight)
                TableCell(text = "M", weight = column2Weight)
                TableCell(text = "4", weight = column3Weight)
            }
        }

    }
}

@Composable
fun SelectMediumScreen(modifier : Modifier = Modifier, showNextScreen : () -> Unit){
    val radioOptions = listOf("Add Text", "Add Logo", "Add Icon", "Add Effect or Filter", "Continue to Upload")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[2]) }

    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = modifier,
            text = "SNAPPRINT",
            fontSize = 70.sp,
            textAlign = TextAlign.Center
        )

        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                modifier = modifier,
                text = "Current Mediums",
                textAlign = TextAlign.Center
            )
            TableScreen2()
        }

        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                modifier = modifier,
                text = "Select Mediums",
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
            Button(onClick = showNextScreen) {
                Text("Next")
            }
        }
    }
}

@Preview
@Composable
fun SelectMediumPreview(){

    SnapPrintTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background){
            SelectMediumScreen(modifier = Modifier, {"do nothing"})
        }
    }
}