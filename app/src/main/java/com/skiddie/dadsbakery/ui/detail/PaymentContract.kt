package com.skiddie.dadsbakery.ui.detail

interface PaymentContract {

    interface View : com.skiddie.dadsbakery.base.BaseView {
        fun onCheckoutSuccess(checkoutResponse: com.skiddie.dadsbakery.model.reponse.checkout.CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter : PaymentContract, com.skiddie.dadsbakery.base.BasePresenter {
        fun getCheckout(foodId:String, userId:String, quantity:String, total:String, view: android.view.View)
    }

}