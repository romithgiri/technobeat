package com.rohit.technbeat.UserModules

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.rohit.technbeat.Admin.Admin.AdminMenu
import com.rohit.technbeat.Admin.AdminPanelSelection
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_menu.*

class menu : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        toolbaradmin.setTitle("Admin Login")
        toolbaradmin.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })

        loginadmin.setOnClickListener {
            val user = mAuth.currentUser
            val uid = user!!.uid
            mDatabase= FirebaseDatabase.getInstance().getReference("user").child(uid)
            mDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.child("Pass").exists()) {
                        val intent=Intent(this@menu, AdminPanelSelection::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
            Toast.makeText(this, "You Are Not An Admin.)", Toast.LENGTH_LONG).show()
        }
    }

    fun adminverify(){

    }
}