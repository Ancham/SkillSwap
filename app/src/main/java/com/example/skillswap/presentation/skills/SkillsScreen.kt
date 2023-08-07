package com.example.skillswap.presentation.skills

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.skillswap.data.Skill

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SkillsScreen(
    viewModel: SkillsViewModel = hiltViewModel()
) {

    val skills = viewModel.skills.value

    Scaffold(
        floatingActionButton = { FloatingButton(viewModel) }
    ) {
    }
    LazyColumn(
        content = {
            skills?.let {
                items(it.size) { index ->
                    Text(text = it[index].name)
                }
            }
        }
    )

}

@Composable
fun FloatingButton(viewModel: SkillsViewModel) {
    val openDialog = remember { mutableStateOf(false) }
    FloatingActionButton(
        onClick = {
            openDialog.value = true
        },
        shape = MaterialTheme.shapes.small,
        content = { Icon(Icons.Default.Add, contentDescription = "Add") }
    )

    if (openDialog.value) {
        CustomDialog(viewModel, openDialog)
    }
}

@Composable
fun CustomDialog(viewModel: SkillsViewModel, openDialog: MutableState<Boolean>) {
    Dialog(onDismissRequest = { openDialog.value = false }) {
        Column {
            var skillName by remember { mutableStateOf(TextFieldValue("")) }
            var descriptionName by remember { mutableStateOf(TextFieldValue("")) }
            var swapOffer by remember { mutableStateOf(TextFieldValue("")) }

            TextField(
                value = skillName,
                onValueChange = {
                    skillName = it
                },
                label = { Text("Input skill name") }
            )
            TextField(
                value = descriptionName,
                onValueChange = {
                    descriptionName = it
                },
                label = { Text("Input skill description") }
            )
            TextField(
                value = swapOffer,
                onValueChange = { swapOffer = it },
                label = { Text("Input swap offer") }
            )

            Button(onClick = {
                openDialog.value = false

                viewModel.onAddNewSkill(
                    Skill(
                        name = skillName.text,
                        description = descriptionName.text,
                        swapOffer = swapOffer.text
                    )
                )
                println()
            }) {
                Text(text = "Add new skill")
            }
        }
    }
}
