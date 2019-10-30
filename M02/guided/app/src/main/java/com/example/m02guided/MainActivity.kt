package com.example.m02guided

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m02guided.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_fragment.*
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }



    }

}
