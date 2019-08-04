package com.rohit.technbeat.userModules

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_developer.*

class Developer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)
        toolbar14.setTitle("Developer")
        toolbar14.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })

    }
}
