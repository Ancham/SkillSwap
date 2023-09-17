package com.example.skillswap.presentation.swap

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillswap.data.Skill
import com.example.skillswap.domain.usecases.GetSkillUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SwapOfferViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSkillUseCase: GetSkillUseCase,
) : ViewModel() {

    private val skillId: String = checkNotNull(savedStateHandle["swapOfferId"])

    private var _swapOfferInfo: MutableLiveData<Skill> = MutableLiveData()
    val swapOfferInfo: LiveData<Skill>
        get() = _swapOfferInfo

    private var _showProgress: MutableLiveData<Boolean> = MutableLiveData(false)
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    fun onGetSwapOfferInfo() {
//        _showProgress.postValue(true)

//        getSkillUseCase.action(skillId)
//            .onEach {
//                delay(5000)
//                _swapOfferInfo.postValue(it)
//            }
//            .onCompletion {
//                Log.d("onGetSwapOfferInfo", "")
//            }
//            .catch { Log.e("onGetSwapOfferInfo", it.stackTraceToString()) }
//            .flowOn(Dispatchers.IO)
//            .launchIn(viewModelScope)
//
//        _showProgress.postValue(false)
//
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _showProgress.postValue(true)
                Log.d("onAddNewSkill", "")
                _swapOfferInfo.postValue(getSkillUseCase.action(skillId))

            } catch (exception: Exception) {
                Log.e("onGetSwapOfferInfo", exception.stackTraceToString())

            }
            _showProgress.postValue(false)
        }

    }
}