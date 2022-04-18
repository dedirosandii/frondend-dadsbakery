package com.skiddie.dadsbakery.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.skiddie.dadsbakery.R
import com.skiddie.dadsbakery.ui.auth.AuthActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_signup.*


class SignupFragment : Fragment() {

    private var filePath: Uri ?= null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initDummy()
        initListener()
    }

    private fun initListener() {

        ivProfile.setOnClickListener {
            ImagePicker.with(this)
                    .cameraOnly()
                    .start()
        }

        btnContinue.setOnClickListener {

            val fullname = etFullname.text.toString()
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()

            when {
                fullname.isEmpty() -> {
                    etFullname.error = "Silahkan masukkan Fullname"
                    etFullname.requestFocus()
                }
                email.isEmpty() -> {
                    etEmail.error = "Silahkan masukkan Email"
                    etEmail.requestFocus()
                }
                pass.isEmpty() -> {
                    etPassword.error = "Silahkan masukkan Password"
                    etPassword.requestFocus()
                }
                else -> {

                    val data = com.skiddie.dadsbakery.model.request.RegisterRequest(
                        fullname,
                        email,
                        pass,
                        pass,
                        "",
                        "",
                        "",
                        "",
                        filePath
                    )

                    val bundle = Bundle()
                    bundle.putParcelable("data", data)

                    Navigation
                            .findNavController(it)
                            .navigate(R.id.action_fragmentSignUp_to_fragmentSignUpAddress, bundle)
                    (activity as AuthActivity?)!!.toolbarSignUpAddress()
                }
            }

        }
    }

//    private fun initDummy(){
//        etFullname.setText("irfan")
//        etEmail.setText("irfan@gmail.com")
//        etPassword.setText("1231qa2ws")
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (resultCode) {
            Activity.RESULT_OK -> {
                filePath = data?.data!!

                Glide.with(this)
                        .load(filePath)
                        .apply(RequestOptions.circleCropTransform())
                        .into(ivProfile)

            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

}