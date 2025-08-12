package com.ur4nium.daksh19

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IpqsApiService {
    @GET("api/json/email/{apiKey}/{emailAddress}")
    suspend fun checkEmailReputation(
        @Path("apiKey") apiKey: String,
        @Path("emailAddress") emailAddress: String
    ): Response<EmailReputationResponse>
}