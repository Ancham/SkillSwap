package com.example.skillswap.presentation.swap

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.skillswap.data.Skill
import com.example.skillswap.presentation.progressBar.ProgressBar
import androidx.compose.runtime.LaunchedEffect

@Composable
fun SwapOfferScreen(
    viewModel: SwapOfferViewModel = hiltViewModel(),
    navController: NavHostController,
    appBarTitle: (String) -> Unit,
    canNavigateUp: (Boolean) -> Unit,
    showFloatingButton: (Boolean) -> Unit
) {

    LaunchedEffect(key1 = null) {
        appBarTitle("Swap screen")
        canNavigateUp(true)
        showFloatingButton(false)
    }

    val swapOfferInfo = viewModel.swapOfferInfo.observeAsState().value
    val showProgress = viewModel.showProgress.observeAsState().value


    SwapView(showProgress, swapOfferInfo)

}

@Composable
private fun SwapView(
    showProgress: Boolean?,
    swapOfferInfo: Skill?
) {
    ProgressBar(showProgress) {
        Box(modifier = Modifier.fillMaxSize()/* contentAlignment = Alignment.Center*/) {
            swapOfferInfo?.let {

                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(it.name)
                    Text(it.description)
                    Text(it.swapOffer)

                }
            }

        }

    }
}

