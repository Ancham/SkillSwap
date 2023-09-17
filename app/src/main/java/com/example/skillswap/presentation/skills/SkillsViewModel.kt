package com.example.skillswap.presentation.skills

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.skillswap.domain.usecases.GetSkillsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.skillswap.data.Skill
import com.example.skillswap.domain.usecases.AddSkillsUseCase
import com.example.skillswap.presentation.navigation.ScreenNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@HiltViewModel
class SkillsViewModel @Inject constructor(
    private val getSkillsUseCase: GetSkillsUseCase,
    private val addSkillsUseCase: AddSkillsUseCase
) : ViewModel() {

    private val _skills: LiveData<List<Skill>> = getSkillsUseCase.action().asLiveData()
    val skills: LiveData<List<Skill>>
        get() = _skills

    private val _isSkillNameValid: MutableLiveData<Boolean> by lazy { MutableLiveData(true) }
    val isSkillNameValid: LiveData<Boolean>
        get() = _isSkillNameValid

    private val _isDescriptionNameValid: MutableLiveData<Boolean> by lazy { MutableLiveData(true) }
    val isDescriptionNameValid: LiveData<Boolean>
        get() = _isDescriptionNameValid

    private val _isSwapOfferValid: MutableLiveData<Boolean> by lazy { MutableLiveData(true) }
    val isSwapOfferValid: LiveData<Boolean>
        get() = _isSwapOfferValid

    private val _hideDialog: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val hideDialog: LiveData<Boolean>
        get() = _hideDialog

    fun onAddNewSkill(skill: Skill) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                addSkillsUseCase.action(skill)
                Log.d("onAddNewSkill", "")
            } catch (exception: Exception) {
                Log.e("onAddNewSkill", exception.stackTraceToString())

            }
        }
    }

    fun submitSwapField(skillName: String, description: String, swapOffer: String) {
        viewModelScope.launch(Dispatchers.IO) {
            onSkillNameValidate(skillName)
            onDescriptionNameValidate(description)
            onSwapNameValidate(swapOffer)

            if (validateName(skillName)
                && validateDescription(description)
                && validateSwapName(swapOffer)
            ) {
                onAddNewSkill(
                    Skill(
                        name = skillName,
                        description = description,
                        swapOffer = swapOffer
                    )
                )
                _hideDialog.postValue(true)
            } else {
                _hideDialog.postValue(false)
            }
        }
    }

    fun onSkillNameValidate(skillName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            println("skillNameValidate")
            val validateName = validateName(skillName)
            println("skillNameValidate validateName ${validateName}")
            _isSkillNameValid.postValue(validateName)
        }
    }

    fun onDescriptionNameValidate(description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isDescriptionNameValid.postValue(validateDescription(description))
        }
    }

    fun onSwapNameValidate(swapOffer: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isSwapOfferValid.postValue(validateSwapName(swapOffer))
        }
    }

    private fun validateName(skillName: String) = !skillName.isNullOrEmpty()
    private fun validateDescription(description: String) = !description.isNullOrEmpty()
    private fun validateSwapName(swapOffer: String) = !swapOffer.isNullOrEmpty()
}