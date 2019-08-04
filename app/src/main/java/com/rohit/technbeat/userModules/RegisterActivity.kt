package com.rohit.technbeat.userModules

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference
    lateinit var mProgressbar:ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        toolbar12.setTitle("Sign Up")
        toolbar12.setNavigationOnClickListener { finish() }

        mDatabase=FirebaseDatabase.getInstance().getReference("user")
        mProgressbar=ProgressDialog(this)

        register.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser () {

        val nameTxt = findViewById<View>(R.id.register_name) as EditText
        val emailTxt = findViewById<View>(R.id.register_email) as EditText
        val passwordTxt = findViewById<View>(R.id.register_password) as EditText
        val phoneText=findViewById<View>(R.id.register_phone) as EditText

        val email = emailTxt.text.toString()
        val password = passwordTxt.text.toString()
        val name = nameTxt.text.toString()
        val phoneN0 = phoneText.text.toString()
        val clg=register_clg.text.toString()
        var num1 =0

        if (isEmpty(email)) {
            Toast.makeText(applicationContext, "Enter valid Email address!", Toast.LENGTH_SHORT).show()
            return
        }

        else if (isEmpty(password)) {
            Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
            return
        }

        else if (isEmpty(name)) {
            Toast.makeText(applicationContext, "Enter Name!", Toast.LENGTH_SHORT).show()
            return
        }
        else if (isEmpty(clg)) {
            Toast.makeText(applicationContext, "Enter college Name!", Toast.LENGTH_SHORT).show()
            return
        }
        else if (isEmpty(phoneN0)) {
            Toast.makeText(applicationContext, "Enter Phone Number!", Toast.LENGTH_SHORT).show()
            return
        }
        else if(phoneN0.length <10||phoneN0.length >10) {
            Toast.makeText(applicationContext, "wrong number!", Toast.LENGTH_SHORT).show()
            return
        }
        else if(password.length < 6) {
            Toast.makeText(applicationContext, "Password is too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show()
            return
        }
        else{
            mProgressbar.setMessage("please wait...")
            mProgressbar.show()
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    mProgressbar.dismiss()
                    val user = mAuth.currentUser
                    val uid = user!!.uid
                    mDatabase.child(uid).child("Name").setValue(name)
                    mDatabase.child(uid).child("Email").setValue(email)
                    mDatabase.child(uid).child("Phone_Number").setValue(phoneN0)
                    mDatabase.child(uid).child("College_Name").setValue(clg)

                    mDatabase=FirebaseDatabase.getInstance().getReference("Convert")
                    mDatabase.child(phoneN0).setValue(uid)

                    mDatabase=FirebaseDatabase.getInstance().getReference("PR")
                    mDatabase.child("Phone_Number").child(uid).child("Phone_N0").setValue(phoneN0)
                    mDatabase.child("Phone_Number").child(uid).child("Name").setValue(name)

                    val intent= Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Successfully registered. To continue log in your account :-)", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this, "Error registering, try again later :(", Toast.LENGTH_LONG).show()
                }
                mProgressbar.dismiss()
            })
        }
    }

}
