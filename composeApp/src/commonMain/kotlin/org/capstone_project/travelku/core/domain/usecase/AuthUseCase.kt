package org.capstone_project.travelku.core.domain.usecase

import org.capstone_project.travelku.core.data.utils.Result
import org.capstone_project.travelku.core.domain.model.AuthResultData
import org.capstone_project.travelku.core.domain.repository.IAuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthUseCase: KoinComponent {
    private val repository: IAuthRepository by inject()

    suspend fun login(email: String, password: String): Result<AuthResultData> {
        return repository.login(email, password)
    }
}
