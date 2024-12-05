package app.proj.musicappui.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionView(){

    Column(modifier = Modifier.height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Manage Subscriptions" )

        Card(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(4.dp),

            ) {
            Column (
                modifier = Modifier.padding(8.dp)
            ){
                Column{
                    Text(text = "Musical")
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                        ){
                        Text(text = "Free Tier")
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(text = "See all plans")
                            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "See all plans",
                            )

                        }
                    }

                }
                Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp),color = Color.Blue)

                Row(modifier = Modifier.padding(vertical = 16.dp)){
                    Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
                    Text(text = "Get a Plan")
                }
                

            }
        }
    }
}