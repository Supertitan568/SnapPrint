/*
*  File Name: HomeScreen.kt
*  Author: Lane Odenbach
*  This file displays the user's orders and allows them to sign out too
* */
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snapprint.R
import com.example.snapprint.UserOrder
import com.example.snapprint.ui.theme.SnapPrintTheme

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    //this creates all of the tables cells for the table below
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

@Composable
fun TableScreen(orders: MutableList<UserOrder>) {
    //This function displays a table that is used to show all of the current orders
    //It just takes in the orders
    val column1Weight = .3f // 30%
    val column2Weight = .3f // 70%
    val column3Weight = .3f

    // The LazyColumn is the table here
    LazyColumn(
        Modifier
            .padding(16.dp)) {
        // Here is the top line
        item {
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Order #", weight = column1Weight)
                TableCell(text = "Amount", weight = column2Weight)
                TableCell(text = "Category", weight = column3Weight)
            }
        }
        //This displays 6 orders in the order list
        items(1) {
            for (order in 0..6) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White)) {
                    if (orders.size > order) {
                        TableCell(text = orders[order].orderNum.toString(), weight = column1Weight)
                        TableCell(text = orders[order].amount, weight=column2Weight )
                        TableCell(text = orders[order].orderCategory, weight = column2Weight)
                    }
                    else{
                        TableCell(text = " ", weight = column1Weight)
                        TableCell(text = " ", weight = column2Weight)
                        TableCell(text = " ", weight = column3Weight)
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier : Modifier = Modifier, orders : MutableList<UserOrder>,  showNextScreen: () -> Unit, showLoginScreen: () -> Unit){
    //This displays the homescreen to the user
    //It takes in the user's orders and functions to move between composables
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = modifier,
            text = "SNAPPRINT",
            fontSize = 70.sp,
            textAlign = TextAlign.Center
        )

        Button(onClick = showNextScreen) {
            Text("Place Order")
        }

        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                modifier = modifier,
                text = "Current Orders",
                textAlign = TextAlign.Center
            )
            TableScreen(orders)
        }
        //settings icon
        Image(painter = painterResource(id = R.drawable.settings_icon), contentDescription = null, modifier = Modifier.width(50.dp) )

        Button(onClick = showLoginScreen) {
            Text("Sign Out")
        }

        
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    //This displays the above composable
    SnapPrintTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background){
            HomeScreen(modifier = Modifier, mutableListOf(UserOrder(1, "1/1/1970", "Shirt"), UserOrder(2, "9/11/2001", "Mug"), UserOrder(3, "8/12/2003", "Shirt")), {"Hello"}, {"Hello"})
        }
    }
}