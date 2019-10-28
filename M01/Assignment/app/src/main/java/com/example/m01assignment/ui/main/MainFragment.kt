package com.example.m01assignment.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.m01assignment.MainActivity
import com.example.m01assignment.R
import com.example.m01assignment.doAmorFromInputs
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    lateinit var callback: OnWhateverListener
    val compositeDisposable = CompositeDisposable()
    fun setOnWhateverListener(callback: OnWhateverListener) {
        this.callback = callback
    }



    interface OnWhateverListener {

        fun doAthing()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        setOnWhateverListener(callback)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val something = activity
        tv_display_result.setOnClickListener { callback.doAthing() }


    val etDownPaymentValueObs = et_down_payment.textChanges().filter { it.length > 1 }
    val etInterestRateValueObs = et_interest_rate.textChanges().filter { it.length > 1 }
    val etLoanLengthValueObs = et_loan_length.textChanges().filter { it.length > 1 }
    val etPurchasePriceValueObs = et_purchase_price.textChanges().filter { it.length > 1 }
    val combinedInput: Observable<CharSequence> = Observables.combineLatest<CharSequence, CharSequence,
            CharSequence, CharSequence, CharSequence
            >(etDownPaymentValueObs,
            etInterestRateValueObs,
            etLoanLengthValueObs,
            etPurchasePriceValueObs)
    { dp: CharSequence, ir: CharSequence, ll: CharSequence, pp: CharSequence ->

        "${doAmorFromInputs(dp.toString().toDouble(),ir.toString().toDouble(),ll.toString().toInt(),pp.toString().toDouble())}"
    }
        val disposable = combinedInput.subscribe{
            tv_display_result.text = it
        }

    compositeDisposable.add(disposable)
}
}
/*disposable = obsValueAndDown.subscribe{
    vTextNewValue.text = it
}*/




           /* .observeOn(AndroidSchedulers.mainThread())
            .retry(3)
            .subscribeBy(  // named arguments for lambda Subscribers
                    onComplete = { Log.i(SystemClock.currentThreadTimeMillis().toString(),"now")},
                    onError =  { throwable ->  throwable.printStackTrace() }
            )*/





/* val obsFieldsCombined = Observable.combineLatest(etonefield,ettwofield) {first,last ->
     "$first $last"
*/