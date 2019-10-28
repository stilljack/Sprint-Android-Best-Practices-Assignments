package com.example.m01assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.m01assignment.ui.main.MainFragment
import com.example.m01assignment.ui.main.MainFragment.OnWhateverListener

class MainActivity : AppCompatActivity(), OnWhateverListener {
    override fun doAthing() {
        Toast.makeText(this,"something",Toast.LENGTH_SHORT).show()
    }
    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is MainFragment) {
            fragment.setOnWhateverListener(this)
        }
    }
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
