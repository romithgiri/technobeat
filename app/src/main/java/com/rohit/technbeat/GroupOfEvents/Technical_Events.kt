package com.rohit.technbeat.GroupOfEvents

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import com.rohit.technbeat.Book
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_technical__events.*

class Technical_Events : AppCompatActivity() {
    lateinit var mDatabase : DatabaseReference
    lateinit var mProgressbar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technical__events)
        toolbar13.setTitle("Technical Events")
        toolbar13.setNavigationOnClickListener(object : View.OnClickListener {
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
                val one=dataSnapshot.child("technical").child("one").getValue(String::class.java)
                val two=dataSnapshot.child("technical").child("two").getValue(String::class.java)
                val three=dataSnapshot.child("technical").child("three").getValue(String::class.java)
                val four=dataSnapshot.child("technical").child("four").getValue(String::class.java)
                val five=dataSnapshot.child("technical").child("five").getValue(String::class.java)
                t1.setText(one)
                t2.setText(two)
                t3.setText(three)
                t4.setText(four)
                t5.setText(five)
                mProgressbar.dismiss()
            }
            override fun onCancelled(p0: DatabaseError?) {
            }
        })
        t1.setOnClickListener {
            var abc=t1.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        t2.setOnClickListener {
            var abc=t2.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        t3.setOnClickListener {
            var abc=t3.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        t4.setOnClickListener {
            var abc=t4.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        t5.setOnClickListener {
            var abc=t5.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }

    }
}
