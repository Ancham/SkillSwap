package com.example.skillswap.presentation.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.Dialog
import com.example.skillswap.presentation.skills.SkillsViewModel

@Composable
fun CustomDialog(viewModel: SkillsViewModel, openDialog: MutableState<Boolean>) {
    Dialog(onDismissRequest = { openDialog.value = false }) {
        Column {
            var skillName by remember { mutableStateOf(TextFieldValue()) }
            var descriptionName by remember { mutableStateOf(TextFieldValue()) }
            var swapOffer by remember { mutableStateOf(TextFieldValue()) }

            val isSkillNameValid = viewModel.isSkillNameValid.observeAsState().value
            val isDescriptionNameValid = viewModel.isDescriptionNameValid.observeAsState().value
            val isSwapOfferValid = viewModel.isSwapOfferValid.observeAsState().value
            val hideDialog = viewModel.hideDialog.observeAsState().value


            isSkillNameValid?.let {
                TextField(
                    value = skillName,
                    onValueChange = {
                        skillName = it
                        viewModel.onSkillNameValidate(it.text)
                    },
                    isError = !isSkillNameValid,
                    label = { Text("Input skill name") }
                )
            }

            isDescriptionNameValid?.let {
                TextField(
                    value = descriptionName,
                    onValueChange = {
                        descriptionName = it
                        viewModel.onDescriptionNameValidate(it.text)
                    },
                    isError = !isDescriptionNameValid,
                    label = { Text("Input skill description") }
                )
            }

            isSwapOfferValid?.let {
                TextField(
                    value = swapOffer,
                    onValueChange = {
                        swapOffer = it
                        viewModel.onSwapNameValidate(it.text)

                    },
                    isError = !isSwapOfferValid,
                    label = { Text("Input swap offer") }
                )
            }

            Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {

                viewModel.submitSwapField(skillName.text, descriptionName.text, swapOffer.text)

                hideDialog?.let {
                    println("hide dialog ${it}")
                    openDialog.value = it
                }

            }) {
                Text(text = "Add new skill")
            }
        }
    }
}