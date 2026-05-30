package com.panini.ticketsupport.domain

import com.panini.ticketsupport.data.remote.dto.UserDto

object AuthSession {
    var currentUser: UserDto? = null
        private set

    fun setUser(user: UserDto) {
        currentUser = user
    }

    fun clear() {
        currentUser = null
    }
}
