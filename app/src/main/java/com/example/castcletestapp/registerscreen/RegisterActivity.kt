package com.example.castcletestapp.registerscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.castcletestapp.R
import com.example.castcletestapp.registerscreen.ui.phonenumberinput.PhoneNumberInputFragment

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PhoneNumberInputFragment.newInstance())
                .commitNow()
        }
    }
}