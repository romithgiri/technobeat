package com.rohit.technbeat.userModules

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rohit.technbeat.groupOfEvents.*
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_events.*

class Events : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        toolbar9.setTitle("Events Categories")
        toolbar9.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })

        technical.setOnClickListener {
            var abc="TECHNICAL"
            val intent=Intent(this, Technical_Events::class.java)
            intent.putExtra("EventGrpRequest",abc)
            startActivity(intent)

        }

        gaming.setOnClickListener {
            var abc="GAMING"
            val intent=Intent(this, GamingEvents::class.java)
            intent.putExtra("EventGrpRequest",abc)
            startActivity(intent)
        }
        media.setOnClickListener {
            var abc="MEDIA"
            val intent=Intent(this, MediaEvents::class.java)
            intent.putExtra("EventGrpRequest",abc)
            startActivity(intent)
        }

        corporate.setOnClickListener {
            var abc="CORPORATE"
            val intent=Intent(this, CorporateEvents::class.java)
            intent.putExtra("EventGrpRequest",abc)
            startActivity(intent)
        }

        photography.setOnClickListener {
            var abc="PHOTOGRAPHY"
            val intent=Intent(this, PhotographyEvents::class.java)
            intent.putExtra("EventGrpRequest",abc)
            startActivity(intent)
        }

        flagship.setOnClickListener {
            var abc="FLAGSHIP"
            val intent=Intent(this, FlagshipEvents::class.java)
            intent.putExtra("EventGrpRequest",abc)
            startActivity(intent)
        }

    }
}
