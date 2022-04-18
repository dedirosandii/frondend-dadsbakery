package com.skiddie.dadsbakery.ui.detail

import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PaymentPresenter (private  val view:PaymentContract.View) : PaymentContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getCheckout(foodId:String, userId:String, quantity:String, total:String, view: View) {
        this.view.showLoading()
        val disposable = com.skiddie.dadsbakery.network.HttpClient.getInstance().getApi()!!.checkout(
                foodId,
                userId,
                quantity,
                total,
                "DELIVERED"
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            this.view.dismissLoading()

                            if (it.meta?.status.equals("success", true)) {
                                it.data?.let { it1 -> this.view.onCheckoutSuccess(it1, view) }
                            } else {
                                it.meta?.message?.let { it1 -> this.view.onCheckoutFailed(it1) }
                            }

                        },
                        {
                            this.view.dismissLoading()
                            this.view.onCheckoutFailed(it.message.toString())
                        })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {}

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}