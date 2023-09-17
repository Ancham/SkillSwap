package com.example.skillswap.presentation.skills

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.skillswap.R
import com.example.skillswap.data.Skill
import com.example.skillswap.presentation.navigation.ScreenNavigation

@Composable
fun SkillsScreen(
    viewModel: SkillsViewModel = hiltViewModel(),
    navController: NavHostController,
    context: Context,
    appBarTitle: (String) -> Unit,
    canNavigateUp: (Boolean) -> Unit,
    showFloatingButton: (Boolean) -> Unit,
) {

    LaunchedEffect(key1 = null) {
        appBarTitle("Skills screen")
        canNavigateUp(false)
        showFloatingButton(true)
    }

    val skills = viewModel.skills.observeAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SkillView(skills, navController, context)
    }
}


@Composable
fun SkillView(skills: List<Skill>?, navController: NavHostController, context: Context) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        content = {
            skills?.let {
                items(it.size) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(20.dp)
                            .background(Color.Gray)
                            .clickable(enabled = true, onClick = {
                                navController.navigate(ScreenNavigation.SwapOfferScreen.route + "/" + skills[index].skillId)
                            }),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Column(
                        ) {
                            Text(text = "Name: ")
                            Text(text = skills[index].name)
                        }
                        Column(
                        ) {
                            Text(text = "Description: ")
                            Text(text = skills[index].description)
                        }
                        Column(
                        ) {
                            Text(text = "SwapOffer: ")
                            Text(text = skills[index].swapOffer)
                        }

                        Box(modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                            .padding(5.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_TEXT, skills[index].swapOffer)
                                }

                                context.startActivity(Intent.createChooser(intent, "tyst"))

                            }) {
                            Image(
                                contentDescription = null,
                                painter = painterResource(id = R.drawable.share)
                            )
                        }
                    }
                }
            }

        })
}


