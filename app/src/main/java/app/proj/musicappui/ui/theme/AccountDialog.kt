package app.proj.musicappui.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties


//Add account drawer option Screen
@Composable
fun AccountDialog(dialogOpen : MutableState<Boolean>){
    if (dialogOpen.value){
        AlertDialog(
            onDismissRequest = {
                dialogOpen.value = false
            },
            confirmButton = {
                TextButton(onClick = {
                    dialogOpen.value = false
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                    TextButton(onClick = {
                        dialogOpen.value = false
                    }) {
                        Text(text = "Dismiss")
                    }
            },
            title = {
                Text(text = "AddAccount")
            },
            text = {
                Column(modifier = Modifier
                    .wrapContentHeight()
                    .padding(top = 16.dp),
                    verticalArrangement = Arrangement.Center) {
                    TextField(value = "", onValueChange = {

                    },
                        label = { Text(text = "Enter Email ID") })
                    TextField(value = "", onValueChange = {

                    },label = { Text(text = "Enter Password") }
                    )
                }
            },
            modifier = Modifier.fillMaxWidth().background(Color.Green)
                .padding(8.dp),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color.White,
            contentColor = Color.Blue,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
}