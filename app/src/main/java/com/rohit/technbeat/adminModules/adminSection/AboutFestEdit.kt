package com.rohit.technbeat.adminModules.adminSection

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_about_fest_edit.*

class AboutFestEdit : AppCompatActivity() {

    lateinit var mDatabase : DatabaseReference
    lateinit var mProgressbar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_fest_edit)

        mProgressbar= ProgressDialog(this)
        mProgressbar.setMessage("Loading...")
        mProgressbar.show()
        mProgressbar.setCanceledOnTouchOutside(false)

        //fetching value from database for entry fee and description
        mDatabase= FirebaseDatabase.getInstance().getReference("Fest").child("aboutFest")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val one=dataSnapshot.getValue(String::class.java)
                aboutTextEdit.setText(one)
                mProgressbar.dismiss()
            }
            override fun onCancelled(p0: DatabaseError?) {
            }
        })

        btnEditAboutText.setOnClickListener {
            var textVal=aboutTextEdit.text.toString()
            mProgressbar.setMessage("please wait...")
            mProgressbar.show()
            mProgressbar.setCanceledOnTouchOutside(false)

            mDatabase.child("aboutFest").setValue(textVal)
            val intent= Intent(this, AdminMenu::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_LONG).show()
            mProgressbar.dismiss()
        }
    }
}
