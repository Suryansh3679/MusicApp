package app.proj.musicappui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.TextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AccountfeDialog(dialogOpen : MutableState<Boolean>){
    if (dialogOpen.value){
        AlertDialog(
            onDismissRequest = {
                dialogOpen.value =false
            },
            confirmButton = {
                TextButton(onClick = {
                    dialogOpen.value =false
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton =  {
                TextButton(onClick = {
                    dialogOpen.value =false
                }) {
                    Text(text = "Dismiss")
                }
            },
            title = {
                Text(text = "Add Account")
            },
            text = {
                Column(modifier = Modifier
                    .wrapContentHeight()
                    .padding(top = 16.dp),
                    verticalArrangement = Arrangement.Center) {
                    TextField(value = "", onValueChange = {
                        
                    },
                        label = { Text(text = "Enter Email ID")})
                    TextField(value = "", onValueChange = {
                        
                    },label = { Text(text = "Enter Password")}
                    )
                }
            },
            titleContentColor = Color.Blue,
            modifier = Modifier.background(Color.Green)
        )
    }
}