package com.example.daggerguided

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.toColor
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var  dataExample:DataExampleContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val example = DataExample

        textview.text = "${example.getDate()}"

    }
}

interface DataExampleContract {
    fun getDate():Long


}

class Sauce:DataExampleContract {
    override fun getDate(): Long {

        return dateone
    }
    val dateone = Date().time

}

class SauceTwo:DataExampleContract {
    override fun getDate(): Long {

        return 1572422942211111
    }
    val datetoo = Date().time

}