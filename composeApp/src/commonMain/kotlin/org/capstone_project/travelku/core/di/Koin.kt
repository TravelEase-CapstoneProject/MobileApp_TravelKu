package org.capstone_project.travelku.core.di

import org.capstone_project.travelku.core.data.local.preferences.UserPreferences
import org.capstone_project.travelku.core.data.local.preferences.createDataStore
import org.capstone_project.travelku.core.data.remote.repository.auth.AuthRepositoryImpl
import org.capstone_project.travelku.core.data.remote.repository.auth.AuthService
import org.capstone_project.travelku.core.data.utils.provideDispatcher
import org.capstone_project.travelku.core.domain.repository.IAuthRepository
import org.capstone_project.travelku.core.domain.usecase.AuthUseCase
import org.capstone_project.travelku.ui.presentation.screen.auth.login.LoginViewModel
import org.capstone_project.travelku.ui.presentation.screen.auth.register.RegisterViewModel
import org.capstone_project.travelku.ui.presentation.screen.common.SharedViewModel
import org.capstone_project.travelku.ui.presentation.screen.main.home.HomeViewModel
import org.koin.dsl.module

val appModule = module {
    factory { AuthService() }
    single<IAuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    single { AuthUseCase() }
    single { UserPreferences(createDataStore()) }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val viewModelModule = module {
    factory { LoginViewModel(get()) }
    factory { RegisterViewModel(get()) }
    factory { SharedViewModel(get()) }
    factory { HomeViewModel(get()) }
}

val koinModules = listOf(appModule, utilityModule, viewModelModule)