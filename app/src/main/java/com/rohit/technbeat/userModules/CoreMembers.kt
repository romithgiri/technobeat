package com.rohit.technbeat.userModules

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_core_members.*

class CoreMembers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core_members)
        toolbar7.setTitle("Core Members")
        toolbar7.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })
    }
}
