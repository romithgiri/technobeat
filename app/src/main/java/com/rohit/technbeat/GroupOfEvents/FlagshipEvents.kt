package com.rohit.technbeat.GroupOfEvents

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import com.rohit.technbeat.Book
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_flagship_events.*

class FlagshipEvents : AppCompatActivity() {
    lateinit var mDatabase : DatabaseReference
    lateinit var mProgressbar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flagship_events)
        toolbar.setTitle("Flagship Events")
        toolbar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })

        var abc2 = intent.getStringExtra("EventGrpRequest")

        mProgressbar= ProgressDialog(this)
        mDatabase= FirebaseDatabase.getInstance().getReference("EventsName")
        mProgressbar.setMessage("Loading...")
        mProgressbar.show()
        mProgressbar.setCanceledOnTouchOutside(false);
        mDatabase= FirebaseDatabase.getInstance().getReference("EventsName")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mProgressbar.setCanceledOnTouchOutside(false);
                val one=dataSnapshot.child("flagship").child("one").getValue(String::class.java)
                val two=dataSnapshot.child("flagship").child("two").getValue(String::class.java)
                val three=dataSnapshot.child("flagship").child("three").getValue(String::class.java)
                val four=dataSnapshot.child("flagship").child("four").getValue(String::class.java)
                f1.setText(one)
                f2.setText(two)
                f3.setText(three)
                f4.setText(four)
                mProgressbar.dismiss()

            }
            override fun onCancelled(p0: DatabaseError?) {
            }
        })
        f1.setOnClickListener {
            var abc=f1.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        f2.setOnClickListener {
            var abc=f2.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        f3.setOnClickListener {
            var abc=f3.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        f4.setOnClickListener {
            var abc=f4.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }

    }
}
