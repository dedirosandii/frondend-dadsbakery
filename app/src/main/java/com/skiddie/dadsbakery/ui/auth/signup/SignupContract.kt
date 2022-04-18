package com.skiddie.dadsbakery.ui.auth.signup

import android.net.Uri

interface SignupContract {

    interface View : com.skiddie.dadsbakery.base.BaseView {
        fun onRegisterSuccess(loginResponse: com.skiddie.dadsbakery.model.reponse.login.LoginResponse, view: android.view.View)
        fun onRegisterPhotoSuccess(view: android.view.View)
        fun onRegisterFailed(message: String)
    }

    interface Presenter : SignupContract, com.skiddie.dadsbakery.base.BasePresenter {
        fun submitRegister(registerRequest: com.skiddie.dadsbakery.model.request.RegisterRequest, view: android.view.View)
        fun submitPhotoRegister(filePath:Uri, view: android.view.View)
    }

}