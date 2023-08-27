package com.example.skillswap.presentation.skillSwap

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.skillswap.R
import com.example.skillswap.presentation.dialog.CustomDialog
import com.example.skillswap.presentation.navigation.ScreenNavigation
import com.example.skillswap.presentation.skills.SkillsScreen
import com.example.skillswap.presentation.skills.SkillsViewModel
import com.example.skillswap.presentation.swap.SwapOfferScreen
import com.example.skillswap.presentation.swap.SwapOfferViewModel

@Composable
fun SkillSwapApp() {
    var appBarTitle by remember { mutableStateOf("") }
    var canNavigateUp by remember { mutableStateOf(false) }
    var showFloatingButton by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            SkillSwapTopAppBar(canNavigateUp, navController, appBarTitle)
        },
        floatingActionButton = {
            if (showFloatingButton) {
                SkillSwapFloatingButton()
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SkillSwapNavHost(
                navController = navController,
                appBarTitle = { appBarTitle = it },
                canNavigateUp = { canNavigateUp = it },
                showFloatingButton = { showFloatingButton = it }
            )

        }
    }

}

@Composable
private fun SkillSwapTopAppBar(
    canNavigateUp: Boolean,
    navController: NavHostController,
    appBarTitle: String
) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = {
            Row {
                if (canNavigateUp) {
                    Box(modifier = Modifier
                        .size(30.dp)
                        .padding(5.dp)
                        .clickable { navController.navigateUp() }) {
                        Image(
                            contentDescription = null,
                            painter = painterResource(id = R.drawable.back)
                        )
                    }
                }
                Text(text = appBarTitle)
            }
        })
}


@Composable
fun SkillSwapNavHost(
    navController: NavHostController,
    appBarTitle: (String) -> Unit,
    canNavigateUp: (Boolean) -> Unit,
    showFloatingButton: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = ScreenNavigation.SkillScreen.route
    ) {

        composable(ScreenNavigation.SkillScreen.route) {
            val viewModel: SkillsViewModel = hiltViewModel()
            val context = LocalContext.current
            SkillsScreen(
                viewModel = viewModel,
                navController = navController,
                context = context,
                appBarTitle = appBarTitle,
                canNavigateUp = canNavigateUp,
                showFloatingButton = showFloatingButton
            )
        }
        composable(
            route = ScreenNavigation.SwapOfferScreen.route + "/{swapOfferId}",
            arguments = listOf(
                navArgument("swapOfferId") { type = NavType.StringType }
            )) {

            val viewModel: SwapOfferViewModel = hiltViewModel()
            SwapOfferScreen(
                viewModel = viewModel,
                navController = navController,
                appBarTitle = appBarTitle,
                canNavigateUp = canNavigateUp,
                showFloatingButton = showFloatingButton
            )
        }
    }

}

@Composable
fun SkillSwapFloatingButton() {
    val openDialog = remember { mutableStateOf(false) }
    FloatingActionButton(
        onClick = {
            openDialog.value = true
        },
        shape = MaterialTheme.shapes.small,
        content = { Icon(Icons.Default.Add, contentDescription = "Add") }
    )

    if (openDialog.value) {
        CustomDialog(hiltViewModel(), openDialog)
    }
}
