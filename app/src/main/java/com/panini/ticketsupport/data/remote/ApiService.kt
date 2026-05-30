package com.panini.ticketsupport.data.remote

import com.panini.ticketsupport.data.remote.dto.CreateTicketRequestDto
import com.panini.ticketsupport.data.remote.dto.LoginRequestDto
import com.panini.ticketsupport.data.remote.dto.TicketDto
import com.panini.ticketsupport.data.remote.dto.UpdateStatusRequestDto
import com.panini.ticketsupport.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequestDto): Response<UserDto>

    @GET("api/tickets")
    suspend fun getTickets(): Response<List<TicketDto>>

    @GET("api/tickets/{id}")
    suspend fun getTicketById(@Path("id") id: String): Response<TicketDto>

    @POST("api/tickets")
    suspend fun createTicket(@Body request: CreateTicketRequestDto): Response<TicketDto>

    @PATCH("api/tickets/{id}/status")
    suspend fun updateStatus(
        @Path("id") id: String,
        @Body request: UpdateStatusRequestDto
    ): Response<Unit>

    @PATCH("api/tickets/{id}/priority")
    suspend fun updatePriority(
        @Path("id") id: String,
        @Body request: UpdateStatusRequestDto
    ): Response<Unit>
}
