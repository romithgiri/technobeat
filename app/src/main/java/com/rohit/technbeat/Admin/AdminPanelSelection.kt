package com.rohit.technbeat.Admin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rohit.technbeat.Admin.Admin.AdminMenu
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_admin_panel_selection.*

class AdminPanelSelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel_selection)
        toolbar17.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })

        adminpanel.setOnClickListener {
            var intent=Intent(this,AdminMenu::class.java)
            startActivity(intent)
            finish()
        }
        eventheadpanel.setOnClickListener {
            var intent=Intent(this,AdminMenu::class.java)
            startActivity(intent)
            finish()
        }
    }
}
