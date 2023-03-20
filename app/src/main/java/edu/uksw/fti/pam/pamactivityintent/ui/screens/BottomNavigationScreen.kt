package edu.uksw.fti.pam.pamactivityintent.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.uksw.fti.pam.pamactivityintent.R
import edu.uksw.fti.pam.pamactivityintent.ui.BottomNavItems
import edu.uksw.fti.pam.pamactivityintent.ui.theme.PAMActivityIntentTheme

var firstName:String = ""
var lastName:String = ""

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItems.Home.screen_route
    ) {
        composable(BottomNavItems.Home.screen_route) {
            HomeScreen2()
        }

        composable(BottomNavItems.Rank.screen_route) {
            RankScreen()
        }
        composable(BottomNavItems.Titles.screen_route) {
            ReadScreen()
        }
        composable(BottomNavItems.Users.screen_route) {
            CommunityScreen()
        }
        composable(BottomNavItems.Profile.screen_route){
            ProfileScreen(firstName, lastName)

        }
        composable("search") {
            SearchScreen2()
        }
    }
}

@Composable
fun BottomNavigation(
    navController: NavController,fname:String,lname:String
){
    firstName = fname
    lastName = lname
    val items = listOf(
        BottomNavItems.Home,
        BottomNavItems.Rank,
        BottomNavItems.Titles,
        BottomNavItems.Users,
        BottomNavItems.Profile
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor = Color(0xFFF0F1F2),
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = "${item.title} icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
                },
                label = {
                    Text(text = stringResource(item.title),
                        fontSize = 9.sp)
                },

                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigationMainScreenView(listNama: ArrayList<String>) {
    val navController = rememberNavController()
    val mCheckedState = remember{ mutableStateOf(false) }
    var lname:String = listNama[1]
    var fname:String = listNama[0]

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.im_logo_1),
                        contentDescription = null,
                        modifier = Modifier
                            .requiredHeight(36.dp)
                            .padding(end = 1.dp)
                    )
                    Text(text = "MangaDex")
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("search"){
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "Localized description")
                    }
                    Switch(checked = mCheckedState.value, onCheckedChange = {mCheckedState.value = it})


                },

                backgroundColor = Color(0xFFF0F1F2),
                elevation = 10.dp
            )
        },
        bottomBar = {
            BottomNavigation(
                navController = navController,fname,lname
            )

        }
    ) {
        NavigationGraph(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun NavvBarPreview() {
    PAMActivityIntentTheme {
        BottomNavigationMainScreenView(arrayListOf("","","",""))
    }
}