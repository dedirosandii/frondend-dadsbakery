package com.skiddie.dadsbakery.ui.detail

import android.app.Dialog
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
import com.skiddie.dadsbakery.R
import com.skiddie.dadsbakery.utils.Helpers.formatPrice
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_payment.*


class PaymentFragment : Fragment(), PaymentContract.View {

    private var progressDialog:Dialog?=null
    lateinit var presenter: PaymentPresenter
    var total:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as DetailActivity).toolbarPayment()

        val data = arguments?.getParcelable<com.skiddie.dadsbakery.model.reponse.home.Data>("data")
        initView(data)
        initView()
        presenter = PaymentPresenter(this)


    }
    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }


    private fun initView(data: com.skiddie.dadsbakery.model.reponse.home.Data?) {

        tvTitle.text = data?.name
        tvPrice.formatPrice(data?.price.toString())

        Glide.with(requireActivity())
                .load(data?.picturePath)
                .into(ivPoster)

        tvNameItem.text = data?.name
        tvHarga.formatPrice(data?.price.toString())

        if (data?.price.toString().isNotEmpty()){
            val totalTax = data?.price?.div(10)
            tvTax.formatPrice(totalTax.toString())

            total = data?.price!!+totalTax!!+ 5000
            tvTotal.formatPrice(total.toString())

        }else {
            tvPrice.text = "IDR. 0"
            tvTax.text = "IDR. 0"
            tvTotal.text = "IDR. 0"
            total = 0
        }

        val user = com.skiddie.dadsbakery.FoodMarket.getApp().getUser()
        val userResponse = Gson().fromJson(user, com.skiddie.dadsbakery.model.reponse.login.User::class.java)

        tvName.text = userResponse?.name
        tvPhoneno.text = userResponse?.phoneNumber
        tvAddress.text = userResponse?.address
        tvHouseNo.text = userResponse?.houseNumber
        tvCity.text = userResponse?.city

        btnCheckout.setOnClickListener {
            presenter.getCheckout(
                    data?.id.toString(),
                    userResponse?.id.toString(),
            "1",
                    total.toString(),
                    it
            )
        }
    }

    override fun onCheckoutSuccess(checkoutResponse: com.skiddie.dadsbakery.model.reponse.checkout.CheckoutResponse, view: View) {

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(checkoutResponse.paymentUrl)
        startActivity(i)

        Navigation.findNavController(view).navigate(R.id.action_fragmenPayment_to_fragmenPaymentSuccess)
    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}