package com.rohit.technbeat.UserModules

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rohit.technbeat.MainActivity
import com.rohit.technbeat.R

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background = object:Thread(){
            override fun run(){
                try{
                    Thread.sleep(600)
                    val intent=Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                }
                catch(e:Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
