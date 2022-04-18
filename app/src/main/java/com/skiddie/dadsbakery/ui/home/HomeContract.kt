package com.skiddie.dadsbakery.ui.home

interface HomeContract {

    interface View : com.skiddie.dadsbakery.base.BaseView {
        fun onHomeSuccess(homeResponse: com.skiddie.dadsbakery.model.reponse.home.HomeResponse)
        fun onHomeFailed(message: String)
    }

    interface Presenter : HomeContract, com.skiddie.dadsbakery.base.BasePresenter {
        fun getHome()
    }

}