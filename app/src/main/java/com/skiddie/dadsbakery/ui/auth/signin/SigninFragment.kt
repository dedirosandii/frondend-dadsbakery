package com.skiddie.dadsbakery.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.skiddie.dadsbakery.R
import com.skiddie.dadsbakery.ui.MainActivity
import com.skiddie.dadsbakery.ui.auth.AuthActivity
import com.skiddie.dadsbakery.utils.Cons
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signin.*


class SigninFragment : Fragment(), SigninContract.View {

    lateinit var presenter : SigninPresenter
    private var progressDialog: Dialog? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SigninPresenter(this)
        if (!com.skiddie.dadsbakery.FoodMarket.getApp().getToken().isNullOrEmpty()) {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }

//        initDummy()
        initView()

        // Masuk melalui Button Signup
        btnSignup!!.setOnClickListener {
            val signup = Intent(activity, AuthActivity::class.java)
            signup.putExtra("page_request", Cons.AUTH_SIGN_UP)
            startActivity(signup)
        }
        // Masuk melalui Button Signin
        btnSignin!!.setOnClickListener {
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()

            when {
                email.isEmpty() -> {
                    etEmail.error = "Silahkan masukkan Email"
                    etEmail.requestFocus()
                }
                pass.isEmpty() -> {
                    etPassword.error = "Silahkan masukkan Password"
                    etPassword.requestFocus()
                }
                else -> {
                    presenter.submitLogin(email, pass)
                }
            }
        }

    }

    override fun onLoginSuccess(loginResponse: com.skiddie.dadsbakery.model.reponse.login.LoginResponse) {
        com.skiddie.dadsbakery.FoodMarket.getApp().setToken(loginResponse.accessToken)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        com.skiddie.dadsbakery.FoodMarket.getApp().setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

//    private fun initDummy(){
//        etEmail.setText("jennie.kim@blackpink.co")
//        etPassword.setText("12345678")
//    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }


    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }


}