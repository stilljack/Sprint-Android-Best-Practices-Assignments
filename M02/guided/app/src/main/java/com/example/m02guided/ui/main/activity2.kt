package com.example.m02guided.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.m02guided.R
import com.example.m02guided.dropBreadCrumbs
import kotlinx.android.synthetic.main.activity_activity2.*

class activity2 : AppCompatActivity() {

    var list = listOf<String>("one","two","three")
    var index =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity2)
        btn.setOnClickListener {
            dropBreadCrumbs("activity 2")
            tv.text=list[index]
            index++
        }
    }
}
