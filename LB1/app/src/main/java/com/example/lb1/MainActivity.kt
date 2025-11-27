package com.example.lb1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.lb1.ui.routes.Home
import com.example.lb1.ui.routes.More
import com.example.lb1.ui.routes.NotificationScreen
import com.example.lb1.ui.routes.routes
import com.example.lb1.ui.screens.HomeScreen
import com.example.lb1.ui.screens.MoreScreen
import com.example.lb1.ui.screens.notifications.NotificationDetailsScreen
import com.example.lb1.ui.screens.notifications.NotificationsScreen
import com.example.lb1.ui.theme.Lb1Theme
import com.example.lb1.viewmodels.NotificationsScreenVM
import androidx.compose.ui.graphics.Color

val notificationsScreenVM = NotificationsScreenVM()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lb1Theme {
                Lb1App()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewScreenSizes
@Composable
fun Lb1App() {
    var currentDestinationPath by rememberSaveable { mutableStateOf(Home.path) }

    val navController = rememberNavController()


    NavigationSuiteScaffold(
        navigationSuiteItems = {
            routes.forEach { appDestination ->
                item(
                    icon = {
                        Icon(
                            appDestination.icon,
                            contentDescription = appDestination.path
                        )
                    },
                    label = { Text(appDestination.path) },
                    selected = appDestination.path == currentDestinationPath,
                    onClick = {
                      currentDestinationPath = appDestination.path
                      navController.navigate(appDestination.path) {}
                    }
                )
            }
        }
    ) {
      Scaffold(

      ) { innerPadding ->
        NavHost(navController = navController, startDestination = Home.path ) {
          composable(Home.path) {
            HomeScreen(modifier = Modifier.padding(innerPadding))
          }
          navigation(startDestination = NotificationScreen.startDestination, route = "notifs") {
            composable(NotificationScreen.path) {
              NotificationsScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = notificationsScreenVM,
                navController = navController
              )
            }
            composable(NotificationScreen.NotificationDetailsScreen.path) {
              NotificationDetailsScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = notificationsScreenVM,
                navController = navController
              )
            }
          }
          composable(More.path) {
            MoreScreen(modifier = Modifier.padding(innerPadding))
          }
        }
      }
        /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            when (currentDestination) {
                AppDestinations.HOME -> HomeScreen(modifier = Modifier.padding(innerPadding))
                AppDestinations.NOTIFICATIONS -> NotificationsScreen(modifier = Modifier.padding(innerPadding), navController = navController)
                AppDestinations.MORE -> MoreScreen(modifier = Modifier.padding(innerPadding))
            }
        }*/
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
    NOTIFICATIONS("Notifications", Icons.Default.Notifications),
    MORE("More", Icons.Default.MoreVert),
}