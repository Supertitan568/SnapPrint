import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.snapprint.ui.theme.SnapPrintTheme

@Composable
fun ThankYouScreen(modifier : Modifier = Modifier, showNextScreen : () -> Unit){
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = modifier,
            text = "SNAPPRINT",
            fontSize = 70.sp,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = modifier,
            text = "Thank you!",
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = modifier,
            text = "Your order has been sent to processing",
            fontSize = 40.sp,
            lineHeight = 50.sp,
            textAlign = TextAlign.Center
        )

        Button(onClick = showNextScreen) {
            Text("Home")
        }

    }
}

@Preview
@Composable
fun ThankYouScreenPreview(){

    SnapPrintTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background){
            ThankYouScreen(modifier = Modifier, {"Do nothing"})
        }
    }
}