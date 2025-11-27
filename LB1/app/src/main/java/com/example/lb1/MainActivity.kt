package com.example.lb1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.lb1.viewmodels.HomeScreenVM

val notificationsScreenVM = NotificationsScreenVM()
val homeScreenVM = HomeScreenVM()

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
      val navBackStackEntry by navController.currentBackStackEntryAsState()
      val currentEntryLabel = navBackStackEntry?.arguments?.getString("label")

      val canNavigateUp = navController.previousBackStackEntry != null

      val currentDestination = navBackStackEntry?.destination
      var isNestedChild = false

      if (currentDestination != null) {
        val parent = currentDestination.parent

        // It is now safe to access .graph because a destination exists
        val rootGraph = navController.graph

        if (parent != rootGraph) {
          isNestedChild = parent?.startDestinationId != currentDestination.id
        }
      }

      Log.d("CURRENT", "$isNestedChild, $canNavigateUp")

      Scaffold(
        topBar = {
          TopAppBar(
            title = {
              Text(
                text = "$currentEntryLabel",
              )
            },
            navigationIcon = {
              if (canNavigateUp && isNestedChild) {
                IconButton(
                  onClick = { navController.navigateUp() }
                ) {
                  Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                  )
                }
              }
            },
          )
        }
      ) { innerPadding ->
        NavHost(navController = navController, startDestination = Home.path ) {
          composable(
            route = Home.path,
            arguments = listOf(
              navArgument("label") {
                type = NavType.StringType
                defaultValue = "Home Screen"
              }
            )
          ) {
            HomeScreen(
              modifier = Modifier.padding(innerPadding),
              viewModel = homeScreenVM
            )
          }
          navigation(
            startDestination = NotificationScreen.startDestination,
            route = "notifs"
          ) {
            composable(
              route = NotificationScreen.path,
              arguments = listOf(
                navArgument("label") {
                  type = NavType.StringType
                  defaultValue = "Notifications Screen"
                }
              )
            ) {
              NotificationsScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = notificationsScreenVM,
                navController = navController
              )
            }
            composable(
              route = NotificationScreen.NotificationDetailsScreen.path,
              arguments = listOf(
                navArgument("label") {
                  type = NavType.StringType
                  defaultValue = "Notif Details Screen"
                }
              )
            ) { navBackStackEntry ->
              val notifId = navBackStackEntry.arguments?.getString("notifId")

              NotificationDetailsScreen(
                modifier = Modifier.padding(innerPadding),
                notificationId = notifId!!.toInt(),
                viewModel = notificationsScreenVM
              )
            }
          }
          composable(
            route = More.path,
            arguments = listOf(
              navArgument("label") {
                type = NavType.StringType
                defaultValue = "More Screen"
              }
            )
          ) {
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