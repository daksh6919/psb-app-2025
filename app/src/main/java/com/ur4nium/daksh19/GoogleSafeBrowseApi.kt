package com.ur4nium.daksh19

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleSafeBrowseApi {
    @POST("v4/threatMatches:find")
    suspend fun checkUrl(
        @Query("key") apiKey: String,
        @Body request: SafeBrowseRequest
    ): Response<SafeBrowseResponse>
}