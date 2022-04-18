package com.skiddie.dadsbakery.ui.auth.signin

interface SigninContract {

    interface View : com.skiddie.dadsbakery.base.BaseView {
        fun onLoginSuccess(loginResponse: com.skiddie.dadsbakery.model.reponse.login.LoginResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter : SigninContract, com.skiddie.dadsbakery.base.BasePresenter {
        fun submitLogin(email:String, password:String)
    }

}