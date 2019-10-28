package com.example.m01assignment.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.m01assignment.MainActivity
import com.example.m01assignment.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    lateinit var callback: OnWhateverListener

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
    }

}
