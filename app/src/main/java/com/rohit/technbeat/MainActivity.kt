package com.rohit.technbeat

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rohit.technbeat.UserModules.FeedAndNavigate
import com.rohit.technbeat.UserModules.LoginActivity
import com.rohit.technbeat.UserModules.RegisterActivity
import com.rohit.technbeat.UserModules.ResetPassword
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cm=baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=cm.activeNetworkInfo
        if (networkInfo!=null && networkInfo.isConnected){
            if(networkInfo.type==ConnectivityManager.TYPE_WIFI)
            {
                internetoff.visibility=View.INVISIBLE
                WhenNet.visibility=View.VISIBLE
            }
            if(networkInfo.type==ConnectivityManager.TYPE_MOBILE)
            {
                internetoff.visibility=View.INVISIBLE
                WhenNet.visibility=View.VISIBLE
            }
        }
        else{
            internetoff.visibility=View.VISIBLE
            WhenNet.visibility=View.INVISIBLE
            login_form.visibility=View.INVISIBLE
            Toast.makeText(baseContext,"NO INTERNET",Toast.LENGTH_LONG).show()
        }

        val token=getSharedPreferences("UserEmail", Context.MODE_PRIVATE)

        if(token.getString("UserEmail","")!="")
        {

            if (networkInfo!=null && networkInfo.isConnected){
                if(networkInfo.type==ConnectivityManager.TYPE_WIFI)
                {
                    val intent=Intent(this, FeedAndNavigate::class.java)
                    startActivity(intent)
                    finish()
                }
                if(networkInfo.type==ConnectivityManager.TYPE_MOBILE)
                {
                    val intent=Intent(this, FeedAndNavigate::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            else{
                internetoff.visibility=View.VISIBLE
                WhenNet.visibility=View.INVISIBLE
                login_form.visibility=View.INVISIBLE
                Toast.makeText(baseContext,"NO INTERNET",Toast.LENGTH_LONG).show()
            }

            /*val intent=Intent(this, FeedAndNavigate::class.java)
            startActivity(intent)
            finish()*/
        }

        login.setOnClickListener {
            val intent=Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        newaccount.setOnClickListener {
            val intent=Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        forgot_password.setOnClickListener {
            val intent=Intent(this, ResetPassword::class.java)
            startActivity(intent)
            finish()
        }
    }
}
