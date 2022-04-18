package com.skiddie.dadsbakery.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.skiddie.dadsbakery.R
import com.skiddie.dadsbakery.utils.Cons
import kotlinx.android.synthetic.main.layout_toolbar.*

class  AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //ambil value
        val pageRequest = intent.getIntExtra("page_request", 0 )
        if (pageRequest == Cons.AUTH_SIGN_UP) {
            toolbarSignUp()
            val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.fragmentSignIn, true)
                    .build()
            Navigation.findNavController(findViewById(R.id.authHostFragment))
                    .navigate(R.id.action_fragmentSignIn_to_fragmentSignUp, null, navOptions)
        }
    }

    private fun toolbarSignUp() {
        toolbar.title = "Sign Up"
        toolbar.subtitle = "Register and eat"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpAddress() {
        toolbar.title = "Address"
        toolbar.subtitle = "Make sure it’s valid"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpSuccess() {
        toolbar.visibility = View.GONE
    }


}