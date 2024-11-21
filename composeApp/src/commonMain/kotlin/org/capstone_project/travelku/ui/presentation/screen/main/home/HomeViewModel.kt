package org.capstone_project.travelku.ui.presentation.screen.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.capstone_project.travelku.core.data.local.preferences.UserPreferences
import org.koin.core.component.KoinComponent

class HomeViewModel(private val userPref: UserPreferences): ViewModel(), KoinComponent {
    private val _username = MutableStateFlow("")
    val username get() = _username

    init {
        getUsername()
    }

    private fun getUsername() {
        viewModelScope.launch {
            userPref.getSession().collect { user ->
                _username.value = user.username
            }
        }
    }
}