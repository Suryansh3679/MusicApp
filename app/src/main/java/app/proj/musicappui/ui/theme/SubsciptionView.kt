package app.proj.musicappui.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import app.proj.musicappui.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionView(){

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Manage Subscriptions" )
        Card(onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
            ) {
            Column {
                Column {
                    Text(text = "Musical")
                    Text(text = "Free Tier")
                }
                Row (horizontalArrangement = Arrangement.End){
                    Text(text = "See all plans")
                }
            }
        }
    }
}