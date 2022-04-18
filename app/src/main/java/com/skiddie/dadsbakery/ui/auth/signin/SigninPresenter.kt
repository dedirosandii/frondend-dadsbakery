package com.skiddie.dadsbakery.ui.auth.signin

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SigninPresenter (private  val view:SigninContract.View) : SigninContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitLogin(email: String, password: String) {
        view.showLoading()
        val disposable = com.skiddie.dadsbakery.network.HttpClient.getInstance().getApi()!!.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.dismissLoading()

                            if (it.meta?.status.equals("success")) {
                                it.data?.let { data -> view.onLoginSuccess(data) }
                            } else {
                                view.onLoginFailed(it.meta?.message.toString())
                            }

                        },
                        {
                            view.dismissLoading()
                            view.onLoginFailed(it.message.toString())
                        })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {}

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}