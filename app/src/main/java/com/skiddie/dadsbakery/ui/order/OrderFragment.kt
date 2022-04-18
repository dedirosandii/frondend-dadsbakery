package com.skiddie.dadsbakery.ui.order

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.skiddie.dadsbakery.R
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class OrderFragment : Fragment(), OrderContract.View {

    lateinit var presenter: OrderPresenter
    private var progressDialog: Dialog? = null

    private var inprogressList:ArrayList<com.skiddie.dadsbakery.model.reponse.transaction.Data>? = ArrayList()
    private var pastorderList:ArrayList<com.skiddie.dadsbakery.model.reponse.transaction.Data>? = ArrayList()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        ViewModelProvider(this).get(OrderViewModel::class.java)
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = OrderPresenter(this)
        presenter.getTransaction()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)

            include_toolbar.toolbar.title = "Your Orders"
            include_toolbar.toolbar.subtitle = "Wait for the best meal"

        }
    }


    override fun onTransactionSuccess(transactionResponse: com.skiddie.dadsbakery.model.reponse.transaction.TransactionResponse) {
        if (transactionResponse.data.isNullOrEmpty()){
            ll_empty.visibility = View.VISIBLE
            ll_tab.visibility = View.GONE
            include_toolbar.visibility = View.GONE
        } else{

            for (a in transactionResponse.data.indices) {
                if (transactionResponse.data[a].status.equals("ON_DELIVERY", true)
                        || transactionResponse.data[a].status.equals("PENDING", true)) {
                    inprogressList?.add(transactionResponse.data[a])
                } else if (transactionResponse.data[a].status.equals("DELIVERED", true)
                        || transactionResponse.data[a].status.equals("CANCELLED", true)
                        || transactionResponse.data[a].status.equals("SUCCESS", true)) {
                    pastorderList?.add(transactionResponse.data[a])
                }
            }

            val sectionPagerAdapter = SectionPagerAdapter(
                    childFragmentManager
            )
            sectionPagerAdapter.setData(inprogressList,pastorderList)
            viewPager.adapter = sectionPagerAdapter
            tabLayout.setupWithViewPager(viewPager)

        }
    }

    override fun onTransactionFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}