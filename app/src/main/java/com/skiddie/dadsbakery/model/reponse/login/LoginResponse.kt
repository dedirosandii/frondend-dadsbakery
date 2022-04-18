package com.skiddie.dadsbakery.model.reponse.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @Expose
        @SerializedName("access_token")
        val accessToken: String,
        @Expose
        @SerializedName("token_type")
        val tokenType: String,
        @Expose
        @SerializedName("user")
        val user: com.skiddie.dadsbakery.model.reponse.login.User
)