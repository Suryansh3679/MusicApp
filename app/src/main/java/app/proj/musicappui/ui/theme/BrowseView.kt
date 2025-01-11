package app.proj.musicappui.ui.theme

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.proj.musicappui.R

@Composable
fun Browse(){

    val category = listOf("Hits","Happy","Running","TGIF","Yoga","Old")

    LazyVerticalGrid(columns = GridCells.Fixed(2)){
        items(category){
            cat->
            BrowserItem(cat = cat, drawable = R.drawable.ic_subscribe)
        }

    }
}

@Composable
@Preview(showBackground = true)
fun BrowsePreview(){
    Browse()
}