package com.panini.ticketsupport.data.repository

import com.panini.ticketsupport.core.UserMessages
import com.panini.ticketsupport.data.remote.ApiService
import com.panini.ticketsupport.data.remote.dto.UserDto
import com.panini.ticketsupport.shared.ApiResult

class AuthRepository(private val apiService: ApiService) {

    suspend fun login(email: String, password: String): ApiResult<UserDto> {
        // Mock: accepts any non-empty credentials for the PoC
        return ApiResult.Success(
            UserDto(
                id = "usr-001",
                name = email.substringBefore("@").replaceFirstChar { it.uppercase() },
                email = email
            )
        )
    }
}
