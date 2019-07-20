package com.rohit.technbeat.UserModules

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_about__fest.*

class AboutFest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about__fest)
        toolbar3.setTitle("About Fest")
        toolbar3.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })
    }
}
