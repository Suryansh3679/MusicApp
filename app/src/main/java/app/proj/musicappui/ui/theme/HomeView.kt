package app.proj.musicappui.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(){

    val categories = listOf("Hits","Happy","Running","TGIF","Yoga")
    val grouped = listOf<String>("New Release","Favourites","Top Rated").groupBy {
        it[0]
    }
    LazyColumn{
        grouped.forEach{
            stickyHeader { 
                Text(text = it.value[0], modifier = Modifier.padding(16.dp))
                LazyRow{
                    items(categories){

                    }
                }
            }
        }
    }
}