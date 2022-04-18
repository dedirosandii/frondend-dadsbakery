package com.skiddie.dadsbakery.ui.auth.signup

import android.net.Uri
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class SignupPresenter (private  val view:SignupContract.View) : SignupContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitRegister(registerRequest: com.skiddie.dadsbakery.model.request.RegisterRequest, view: View) {
        this.view.showLoading()
        val disposable = com.skiddie.dadsbakery.network.HttpClient.getInstance().getApi()!!.register(
                registerRequest.name,
                registerRequest.email,
                registerRequest.password,
                registerRequest.password_confirmation,
                registerRequest.address,
                registerRequest.city,
                registerRequest.houseNumber,
                registerRequest.phoneNumber
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            this.view.dismissLoading()

                            if (it.meta?.status.equals("success")) {
                                it.data?.let { data -> this.view.onRegisterSuccess(data, view) }
                            } else {
                                this.view.onRegisterFailed(it.meta?.message.toString())
                            }

                        },
                        {
                            this.view.dismissLoading()
                            this.view.onRegisterFailed(it.message.toString())
                        })
        mCompositeDisposable!!.add(disposable)
    }

    override fun submitPhotoRegister(filePath:Uri, view: View) {
        this.view.showLoading()

        val profileImageFile = File(filePath.path)

        val profileImageRequestBody = profileImageFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val profileImageParams = MultipartBody.Part.createFormData("file", profileImageFile.name, profileImageRequestBody )

        val disposable = com.skiddie.dadsbakery.network.HttpClient.getInstance().getApi()!!.registerPhoto(
                profileImageParams
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            this.view.dismissLoading()

                            if (it.meta?.status.equals("success")) {
                                it.data?.let { _ -> this.view.onRegisterPhotoSuccess(view) }
                            } else {
                                this.view.onRegisterFailed(it.meta?.message.toString())
                            }

                        },
                        {
                            this.view.dismissLoading()
                            this.view.onRegisterFailed(it.message.toString())
                        })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {}

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}