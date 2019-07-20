package com.rohit.technbeat.UserModules

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_follow_us.*

class FollowUs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow_us)
        toolbar5.setTitle("Follow Us")
        toolbar5.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })

        insta.setOnClickListener {
            val intent= Intent(Intent.ACTION_VIEW,Uri.parse("https://instagram.com/mcc_technobeat?utm_source=ig_profile_share&igshid=1vw69ffk9870n"))
            startActivity(intent)
        }
        youtube.setOnClickListener {
            val intent= Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/channel/UCsK4BejVqF6qlovN0DosBTg"))
            startActivity(intent)
        }
        fb.setOnClickListener {
            val intent= Intent(Intent.ACTION_VIEW,Uri.parse("https://facebook.com/mcc.technobeat/"))
            startActivity(intent)
        }
        snap.setOnClickListener {
            val intent= Intent(Intent.ACTION_VIEW,Uri.parse("https://www.snapchat.com/mcc_technobeat"))
            startActivity(intent)
        }
    }
}
