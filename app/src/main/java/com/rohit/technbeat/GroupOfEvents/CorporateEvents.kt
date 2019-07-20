package com.rohit.technbeat.GroupOfEvents

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import com.rohit.technbeat.Book
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_corporate_events.*

class CorporateEvents : AppCompatActivity() {
    lateinit var mDatabase : DatabaseReference
    lateinit var mProgressbar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_corporate_events)
        toolbar1.setTitle("Corporate Events")
        toolbar1.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })


        var abc2 = intent.getStringExtra("EventGrpRequest")

        mProgressbar=ProgressDialog(this)
        mProgressbar.setMessage("Loading...")
        mProgressbar.show()
        mProgressbar.setCanceledOnTouchOutside(false);
        mDatabase= FirebaseDatabase.getInstance().getReference("EventsName")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mProgressbar.setCanceledOnTouchOutside(false);
                val one=dataSnapshot.child("corporate").child("one").getValue(String::class.java)
                val two=dataSnapshot.child("corporate").child("two").getValue(String::class.java)
                val three=dataSnapshot.child("corporate").child("three").getValue(String::class.java)
                c1.setText(one)
                c2.setText(two)
                c3.setText(three)
                mProgressbar.dismiss()
            }
            override fun onCancelled(p0: DatabaseError?) {
            }
        })
        c1.setOnClickListener {
            var abc=c1.text.toString()
            val intent=Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        c2.setOnClickListener {
            var abc=c2.text.toString()
            val intent=Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        c3.setOnClickListener {
            var abc=c3.text.toString()
            val intent=Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
    }
}
