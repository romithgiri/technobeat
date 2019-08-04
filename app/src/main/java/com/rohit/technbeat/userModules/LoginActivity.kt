package com.rohit.technbeat.userModules

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance();
    lateinit var mProgressbar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val token=getSharedPreferences("UserEmail", Context.MODE_PRIVATE)

        toolbar8.setTitle("Log In")
        toolbar8.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })


        mProgressbar=ProgressDialog(this)

        forgot_password.setOnClickListener {
            val intent=Intent(this, ResetPassword::class.java)
            startActivity(intent)
            finish()
        }
        login2.setOnClickListener {
            val emailTxt = findViewById<View>(R.id.r_email) as EditText
            var email = emailTxt.text.toString()
            val passwordTxt = findViewById<View>(R.id.r_password) as EditText
            var password = passwordTxt.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                mProgressbar.setMessage("please wait...")
                mProgressbar.show()
                this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener ( this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, FeedAndNavigate::class.java))
                        var editor=token.edit()
                        editor.putString("UserEmail",email)
                        editor.apply()
                        finish()
                        Toast.makeText(this, "Successfully Logged in :)", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(applicationContext, "Error in Login Please check password or Email.", Toast.LENGTH_SHORT).show()

                        /*mProgressbar.setMessage("Error in Login Please check password or Email. Touch anywhere to continue!")
                        mProgressbar.show()
*/
                    }
                })

            }

        }
    }
}
