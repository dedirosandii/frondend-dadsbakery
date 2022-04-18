package com.skiddie.dadsbakery.ui.profile

interface ProfileContract {
    interface View : com.skiddie.dadsbakery.base.BaseView {
        fun onProfileSuccess(profileResponse: com.skiddie.dadsbakery.model.reponse.ProfileResponse)
        fun onProfileFailed(message: String)
    }

    interface Presenter : ProfileContract, com.skiddie.dadsbakery.base.BasePresenter {
        fun getProfile()
    }
}