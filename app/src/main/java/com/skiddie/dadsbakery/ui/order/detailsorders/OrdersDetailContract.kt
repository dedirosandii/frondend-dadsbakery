package com.skiddie.dadsbakery.ui.order.detailsorders


interface OrdersDetailContract {
    interface View : com.skiddie.dadsbakery.base.BaseView {
        fun onUpdateTransactionSuccess(message: String)
        fun onUpdateTransactionFailed(message: String)
    }

    interface Presenter : OrdersDetailContract, com.skiddie.dadsbakery.base.BasePresenter {
        fun getUpdateTransaction(id:String, status:String)
    }
}