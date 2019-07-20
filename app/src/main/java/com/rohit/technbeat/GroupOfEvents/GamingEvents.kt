package com.rohit.technbeat.GroupOfEvents

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import com.rohit.technbeat.Book
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_gaming_events.*

class GamingEvents : AppCompatActivity() {
    lateinit var mDatabase : DatabaseReference
    lateinit var mProgressbar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gaming_events)
        toolbar6.setTitle("Gaming Events")
        toolbar6.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })

        var abc2 = intent.getStringExtra("EventGrpRequest")
        mProgressbar= ProgressDialog(this)
        mProgressbar.setMessage("Loading...")
        mProgressbar.show()
        mProgressbar.setCanceledOnTouchOutside(false);

        mDatabase= FirebaseDatabase.getInstance().getReference("EventsName")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mProgressbar.setCanceledOnTouchOutside(false);
                val one=dataSnapshot.child("gaming").child("one").getValue(String::class.java)
                val two=dataSnapshot.child("gaming").child("two").getValue(String::class.java)
                val three=dataSnapshot.child("gaming").child("three").getValue(String::class.java)
                val four=dataSnapshot.child("gaming").child("four").getValue(String::class.java)
                g1.setText(one)
                g2.setText(two)
                g3.setText(three)
                g4.setText(four)
                mProgressbar.dismiss()
            }
            override fun onCancelled(p0: DatabaseError?) {
            }
        })
        g1.setOnClickListener {
            var abc=g1.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)

        }
        g2.setOnClickListener {
            var abc=g2.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        g3.setOnClickListener {
            var abc=g3.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventGrpRequest",abc2)
            intent.putExtra("EventNameRequest",abc)
            startActivity(intent)
            //finish()
        }
        g4.setOnClickListener {
            var abc=g4.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventGrpRequest",abc2)
            intent.putExtra("EventNameRequest",abc)
            startActivity(intent)
            //finish()
        }

    }
}
