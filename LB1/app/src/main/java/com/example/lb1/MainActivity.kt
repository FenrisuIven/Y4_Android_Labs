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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.lb1.ui.routes.Home
import com.example.lb1.ui.routes.More
import com.example.lb1.ui.routes.RecipesScreen
import com.example.lb1.ui.routes.routes
import com.example.lb1.ui.screens.HomeScreen
import com.example.lb1.ui.screens.MoreScreen
import com.example.lb1.ui.screens.recipes.RecipeDetailsScreen
import com.example.lb1.ui.screens.recipes.RecipesScreen
import com.example.lb1.ui.theme.Lb1Theme
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.lb1.ui.screens.recipes.NewRecipeScreen
import com.example.lb1.viewmodels.HomeScreenVM

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
                      if (appDestination.icon !== null) {
                        return@item Icon(
                          appDestination.icon!!,
                          contentDescription = appDestination.path
                        )
                      }
                      else if (appDestination.iconId !== null) {
                        return@item Icon(
                          imageVector = ImageVector.vectorResource(appDestination.iconId!!),
                          contentDescription = appDestination.path
                        )
                      }
                    },
                    label = { Text(
                      appDestination.path.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase() else it.toString()
                      })
                    },
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
              modifier = Modifier.padding(innerPadding)
            )
          }
          navigation(
            startDestination = RecipesScreen.startDestination,
            route = "."
          ) {
            composable(
              route = RecipesScreen.path,
              arguments = listOf(
                navArgument("label") {
                  type = NavType.StringType
                  defaultValue = "Recipes Screen"
                }
              )
            ) {
              RecipesScreen(
                modifier = Modifier.padding(innerPadding),
                navController = navController
              )
            }
            composable(
              route = RecipesScreen.RecipeDetailsScreen.path,
              arguments = listOf(
                navArgument("label") {
                  type = NavType.StringType
                  defaultValue = "Recipe Details Screen"
                }
              )
            ) { navBackStackEntry ->
              val recipeId = navBackStackEntry.arguments?.getString("recipeId")

              RecipeDetailsScreen(
                modifier = Modifier.padding(innerPadding),
                recipeId = recipeId!!.toInt()
              )
            }
            composable(
              route = RecipesScreen.NewRecipeScreen.path,
              arguments = listOf(
                navArgument("label") {
                  type = NavType.StringType
                  defaultValue = "New Recipe Screen"
                }
              )
            ) {
              NewRecipeScreen(
                modifier = Modifier.padding(innerPadding),
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