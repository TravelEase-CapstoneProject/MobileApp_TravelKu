package org.capstone_project.travelku.ui.presentation.screen.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.capstone_project.travelku.core.data.local.preferences.UserPreferences
import org.capstone_project.travelku.core.data.remote.response.UserData

class SharedViewModel(private val userPreferences: UserPreferences) : ViewModel() {
    private val _onBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted = _onBoardingCompleted.asStateFlow()

    private val _session = MutableStateFlow<UserData?>(null)
    val session = _session.asStateFlow()

    fun checkOnBoardingCompleted() {
        viewModelScope.launch {
            userPreferences.getOnboardingCompleted().collect {
                _onBoardingCompleted.value = it
            }
        }
    }

    fun checkSession() {
        viewModelScope.launch {
            userPreferences.getSession().collect {
                _session.value = it
            }
        }
    }

    fun saveOnBoardingCompleted() {
        viewModelScope.launch {
            userPreferences.saveOnboardingCompleted()
        }
    }
}