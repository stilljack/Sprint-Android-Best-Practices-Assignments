package com.example.asssignmint.ui.main

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asssignmint.R
import com.example.asssignmint.retrofit.AdviceAPI
import com.example.asssignmint.retrofit.AdviceMsg
import com.example.asssignmint.retrofit.ApplicationClass
import retrofit2.Call
import javax.inject.Inject

class MainFragment (var applicationComponent: Application): Fragment(),AdviceAPI {
    @Inject
        lateinit var mNetworkApi: AdviceAPI

        init {

        }





/*
    companion object {
        fun newInstance() = MainFragment(  (applicationComponent as ApplicationClass).applicationComponent.inject(this))
    }
*/

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        (applicationComponent as ApplicationClass).applicationComponent.inject(this)
        mNetworkApi.randomAdvice()
    }

    override fun randomAdvice(): Call<AdviceMsg> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
