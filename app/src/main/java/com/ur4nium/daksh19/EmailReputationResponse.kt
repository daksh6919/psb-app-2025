package com.ur4nium.daksh19

import com.google.gson.annotations.SerializedName

data class EmailReputationResponse(
    @SerializedName("valid") val isValid: Boolean,
    @SerializedName("disposable") val isDisposable: Boolean,
    @SerializedName("fraud_score") val fraudScore: Int,
    @SerializedName("spam") val isSpam: Boolean
)