package com.rohit.technbeat.UserModules

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPassword : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    lateinit var mProgressbar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        toolbar2.setTitle("Forgot Password")
        toolbar2.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })

        resetPass.setOnClickListener {sendPasswordResetEmail()}
    }

    private fun sendPasswordResetEmail() {

        val email = resetEmail?.text.toString()

        if (!TextUtils.isEmpty(email)) {
            mAuth!!.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val message = "We have sent a rest password email to your email id. please click the reset password link to set your new password\""
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                                val intent= Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                        } else {
                            Toast.makeText(this, "No user found with this email.", Toast.LENGTH_SHORT).show()
                        }
                    }
        } else {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
        }
    }

}
