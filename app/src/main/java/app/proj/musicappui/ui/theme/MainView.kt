package app.proj.musicappui.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import app.proj.musicappui.MainViewModel
import app.proj.musicappui.R
import app.proj.musicappui.Screen
import app.proj.musicappui.screensInBottom
import app.proj.musicappui.screensInDrawer
import app.proj.musicappui.ui.theme.AccountDialog
import app.proj.musicappui.ui.theme.AccountView
import app.proj.musicappui.ui.theme.Browse
import app.proj.musicappui.ui.theme.Home
import app.proj.musicappui.ui.theme.Library
import app.proj.musicappui.ui.theme.SubscriptionView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView(){

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val viewModel : MainViewModel = viewModel()

    val isFullSheet by remember{ mutableStateOf(false) }
    val modifier = if (isFullSheet) Modifier.fillMaxSize() else Modifier.fillMaxWidth()

    //Allow us to find out which "View" we currently at
    val controller : NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val dialogOpen = remember{
        mutableStateOf (false)
    }

    val currentScreen = remember {
        viewModel.currentScreen.value
    }

    val title = remember {
        //  change the currentScreen.Title
        mutableStateOf(currentScreen.title)
    }

    val modalSheetState = androidx.compose.material.rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {it != ModalBottomSheetValue.HalfExpanded}
    )

    val roundedCornerRadius = if (isFullSheet) 0.dp else 12.dp

    val bottomBar : @Composable () -> Unit = {
        if (currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home){
            BottomNavigation (modifier = Modifier.wrapContentSize()){
                screensInBottom.forEach{
                    item ->
                    val isSelected = currentRoute == item.bRoute
                    val tint = if (isSelected) Color.White else Color.Black
                    BottomNavigationItem(selected = currentRoute == item.bRoute,
                        onClick = {
                            controller.navigate(item.bRoute)
                            title.value = item.bTitle
                        },
                        icon = { Icon(tint = tint,painter = painterResource(id = item.icon), contentDescription = item.bTitle)},
                        label = { Text(text = item.bTitle , color = tint)  },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                    )

                }
            }
        }
    }


    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
        MoreBottomSheet(modifier = modifier)
    }) {
        Scaffold (

            bottomBar = bottomBar,

            topBar = {

                TopAppBar(title = { Text(text = title.value)},
                    actions = {
                              IconButton(onClick = {
                                  scope.launch {
                                      if (modalSheetState.isVisible){
                                          modalSheetState.hide()
                                      }else{
                                          modalSheetState.show()
                                      }
                                  }
                              }) {
                                  Icon(imageVector = Icons.Default.MoreVert, contentDescription = null )
                              }
                    },
                    navigationIcon = { IconButton(onClick = {
                        // Open the drawer
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu")
                    }}
                )
            }, scaffoldState = scaffoldState,
            drawerContent = {
                LazyColumn(Modifier.padding(16.dp)){
                    items(screensInDrawer){
                            item ->
                        DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if (item.dTitle == "Add Account"){
                                // open dialog
                                dialogOpen.value = true
                            }else{
                                //dialogOpen.value = true
                                controller.navigate(item.dRoute)
                                title.value = item.dTitle
                            }
                        }
                    }
                }
            }

        ){
            Navigation(navController = controller, viewModel = viewModel, pd = it )

            AccountDialog(dialogOpen = dialogOpen)
        }
    }


}

@Composable
fun DrawerItem (
    selected: Boolean,
    item : Screen.DrawerScreen,
    onDrawerItemClicker: () -> Unit
){
    val background = if (selected) Color.Cyan else Color.White
    Row (
        Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable {
                onDrawerItemClicker()
            }
    ){
        Icon(painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(text = item.dTitle,
            style = MaterialTheme.typography.titleLarge)
    }
}


@Composable
fun MoreBottomSheet(modifier: Modifier){
    Box(
        modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colorScheme.primary)
    ){
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween){
            Row (modifier = Modifier.padding(16.dp)){
                Icon(modifier= Modifier.padding(end = 8.dp),painter = painterResource(id = R.drawable.baseline_settings_24), contentDescription = "Settings")
                Text(text = "Settings", fontSize = 20.sp,color = Color.White)
            }
            Row (modifier = Modifier.padding(16.dp)){
                Icon(modifier= Modifier.padding(end = 8.dp),painter = painterResource(id = R.drawable.ic_baseline_share_24), contentDescription = "Settings")
                Text(text = "Share", fontSize = 20.sp,color = Color.White)
            }
            Row (modifier = Modifier.padding(16.dp)){
                Icon(modifier= Modifier.padding(end = 8.dp),painter = painterResource(id = R.drawable.ic_help_green), contentDescription = "Settings")
                Text(text = "Help", fontSize = 20.sp,color = Color.White)
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
fun Navigation(navController: NavController,viewModel: MainViewModel,pd : PaddingValues){
    // pd used because scaffold gives us pd value which if we don't use it gives us error
    NavHost(navController = navController as NavHostController,
        startDestination = Screen.DrawerScreen.Account.route,modifier = Modifier.padding(pd) ){


        composable(Screen.BottomScreen.Home.route){
            // TODO ADD Home screen
            Home()
        }

        composable(Screen.BottomScreen.Browse.route){
            Browse()
        }
        composable(Screen.BottomScreen.Library.route){
            Library()
        }

        composable(Screen.DrawerScreen.Account.route){
            AccountView()
        }

        composable(Screen.DrawerScreen.Subscription.route){
            SubscriptionView()
        }
    }
}
