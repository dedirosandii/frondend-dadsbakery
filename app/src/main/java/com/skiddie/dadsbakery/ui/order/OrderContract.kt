package com.skiddie.dadsbakery.ui.order

interface OrderContract {

    interface View : com.skiddie.dadsbakery.base.BaseView {
        fun onTransactionSuccess(transactionResponse: com.skiddie.dadsbakery.model.reponse.transaction.TransactionResponse)
        fun onTransactionFailed(message: String)
    }

    interface Presenter : OrderContract, com.skiddie.dadsbakery.base.BasePresenter {
        fun getTransaction()
    }

}