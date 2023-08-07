package com.example.skillswap.presentation.skills

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillswap.domain.usecases.GetSkillsUseCase
import com.example.skillswap.presentation.base.BaseViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.skillswap.data.Skill
import com.example.skillswap.domain.usecases.AddSkillsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class SkillsViewModel @Inject constructor(
    private val getSkillsUseCase: GetSkillsUseCase,
    private val addSkillsUseCase: AddSkillsUseCase
) : ViewModel() {

    private val _skills: MutableLiveData<List<Skill>> = MutableLiveData()
    val skills: LiveData<List<Skill>> = _skills

    init {
        onGetSkills()
    }

    fun onAddNewSkill(skill: Skill) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                addSkillsUseCase.action(skill)
                Log.d("onAddNewSkill", "")
                onGetSkills()
            } catch (exception: Exception) {
                Log.e("onAddNewSkill", exception.stackTraceToString())

            }
        }
    }

    private fun onGetSkills() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _skills.value = getSkillsUseCase.action()
                Log.d("onGetSkills", getSkillsUseCase.action().size.toString())

            } catch (exception: Exception) {
                Log.e("onGetSkills", exception.stackTraceToString())
            }
        }
    }
}