package com.rohit.technbeat
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_about__fest.*
import kotlinx.android.synthetic.main.activity_book_corporate.*


class Book : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference
    lateinit var mProgressbar: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_corporate)

        //toolbar icon click and title
        toolbar6.setTitle("Registration for Event")
        toolbar6.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })

        mProgressbar= ProgressDialog(this)
        mDatabase= FirebaseDatabase.getInstance().getReference("EventsName")
        mProgressbar.setMessage("Loading...")
        mProgressbar.show()

        //getting value from previous activity
        var xyz = intent.getStringExtra("EventNameRequest")
        Ename.setText(xyz)
        var xyz2 = intent.getStringExtra("EventGrpRequest")

        //fetching value from database for entry fee and description
        mDatabase= FirebaseDatabase.getInstance().getReference("PR").child("Entry_Fee").child(xyz2)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val one=dataSnapshot.child(xyz).child("cost").getValue(String::class.java)
                val two=dataSnapshot.child(xyz).child("Dis").getValue(String::class.java)
                entryfee.setText(one)
                disevent.setText(two)
                mProgressbar.dismiss()
            }
            override fun onCancelled(p0: DatabaseError?) {
            }
        })

        // calling PR check function
        PRcheck.setOnClickListener {
            view->InDatabase()
        }
    }


    private fun InDatabase(){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("PR")
        var code = EPRCode.text.toString()
        var xyz = intent.getStringExtra("EventNameRequest")
        var xyz2 = intent.getStringExtra("EventGrpRequest")

        if (isEmpty(code) || code.length<6) {
            Toast.makeText(applicationContext, "Error can not verify PR code. Please check PR code.", Toast.LENGTH_SHORT).show()
            //mProgressbar.setMessage("Error to Verify. Please Enter the code. Touch anywhere to continue!")
            //mProgressbar.show()
        }
        else {
            val user = mAuth.currentUser
            val uid = user!!.uid
            mDatabase= FirebaseDatabase.getInstance().getReference("user").child(uid)
            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    //get phone number from database
                    val phno=dataSnapshot.child("Phone_Number").getValue(String::class.java)

                    //now check that number is register or not in any event
                    myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            if (dataSnapshot.child("Register").child(Ename.text.toString()).child(phno).exists()) {
                                mProgressbar.dismiss()
                                Toast.makeText(applicationContext, "You are already register for this Event.", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        if (dataSnapshot.child("Team").child(EPRCode.text.toString()).exists()) {
                                            val user = mAuth.currentUser
                                            val uid = user!!.uid
                                            val mydate=dataSnapshot.child("Date").getValue(String::class.java)
                                            val phno=dataSnapshot.child("Phone_Number").child(uid).child("Phone_N0").getValue(String::class.java)
                                            val name=dataSnapshot.child("Phone_Number").child(uid).child("Name").getValue(String::class.java)

                                            val mytime=dataSnapshot.child("Entry_Fee").child(xyz2).child(xyz).child("time").getValue(String::class.java)
                                            val mydateval=dataSnapshot.child("Entry_Fee").child(xyz2).child(xyz).child("venue").getValue(String::class.java)

                                            myRef.child("Register").child(xyz).child(phno).child("Name").setValue(name)
                                            myRef.child("Register").child(xyz).child(phno).child("Phone_Number").setValue(phno)

                                            //calculation of income
                                            myRef.child("Income").child(mydate).child(EPRCode.text.toString()).child(uid).child(xyz).setValue(entryfee.text.toString())
                                            var ttin1=dataSnapshot.child("Income").child(mydate).child("Total_Income").getValue(String::class.java)
                                            var ttin3=ttin1.toString()
                                            val ttin4=ttin3.toInt()
                                            val ttin5=entryfee.text.toString()
                                            val ttin6=ttin5.toInt()
                                            val ttin7=ttin4+ttin6
                                            myRef.child("Income").child(mydate).child("Total_Income").setValue(ttin7.toString())

                                            //in participated database
                                            val myRefp = database.getReference("PR").child("Participated").child(uid)
                                            myRefp.child(xyz).child("time").setValue(mytime)
                                            myRefp.child(xyz).child("venue").setValue(mydateval)
                                            myRefp.child(xyz).child("name").setValue(xyz)

                                            //in user databse
                                            val myRef = database.getReference("user").child(uid)
                                            myRef.child("Events").child(xyz).child("Name").setValue(xyz)
                                            myRef.child("Events").child(xyz).child("Register_By").setValue(EPRCode.text.toString())
                                            myRef.child("Events").child(xyz).child("Cost").setValue(entryfee.text.toString())

                                            mProgressbar.setCanceledOnTouchOutside(false)
                                            mProgressbar.setProgress(100)
                                            mProgressbar.setMessage("Registeration Is Done.")
                                            mProgressbar.show()
                                            val handler2 = Handler()
                                            handler2.postDelayed(Runnable { mProgressbar.dismiss() }, 3000)

                                            EPRCode.text.clear()
                                            Toast.makeText(this@Book, "Registration Is Done.", Toast.LENGTH_LONG).show()
                                        }
                                        else {
                                            mProgressbar.dismiss()
                                            Toast.makeText(applicationContext, "Error can not verify PR code. Please check PR code.", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    override fun onCancelled(error: DatabaseError) {
                                    }
                                })

                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                        }
                    })

                }
                override fun onCancelled(p0: DatabaseError?) {
                }
            })
        }
    }
}
