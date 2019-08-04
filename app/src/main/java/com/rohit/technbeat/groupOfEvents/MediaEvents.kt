package com.rohit.technbeat.groupOfEvents
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import com.rohit.technbeat.Book
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_media_events.*

class MediaEvents : AppCompatActivity() {
    lateinit var mDatabase : DatabaseReference
    lateinit var mProgressbar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_events)
        toolbar10.setTitle("Social Media Eevents")
        toolbar10.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })

        var abc2 = intent.getStringExtra("EventGrpRequest")

        mProgressbar= ProgressDialog(this)
        mDatabase= FirebaseDatabase.getInstance().getReference("EventsName")
        mProgressbar.setMessage("Loading...")
        mProgressbar.show()
        mProgressbar.setCanceledOnTouchOutside(false)

        mDatabase= FirebaseDatabase.getInstance().getReference("EventsName")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mProgressbar.setCanceledOnTouchOutside(false);
                val one=dataSnapshot.child("media").child("one").getValue(String::class.java)
                val two=dataSnapshot.child("media").child("two").getValue(String::class.java)
                val three=dataSnapshot.child("media").child("three").getValue(String::class.java)
                val four=dataSnapshot.child("media").child("four").getValue(String::class.java)
                m1.setText(one)
                m2.setText(two)
                m3.setText(three)
                m4.setText(four)
                mProgressbar.dismiss()
            }
            override fun onCancelled(p0: DatabaseError?) {
            }
        })
        m1.setOnClickListener {
            var abc=m1.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        m2.setOnClickListener {
            var abc=m2.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        m3.setOnClickListener {
            var abc=m3.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }
        m4.setOnClickListener {
            var abc=m4.text.toString()
            val intent= Intent(this, Book::class.java)
            intent.putExtra("EventNameRequest",abc)
            intent.putExtra("EventGrpRequest",abc2)
            startActivity(intent)
            //finish()
        }

    }
}
