package com.rohit.technbeat.adminModules.adminSection

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.rohit.technbeat.R
import kotlinx.android.synthetic.main.activity_users_verification.*

class UsersVerification : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference
    lateinit var mDatabase2 : DatabaseReference
    lateinit var mDatabase3 : DatabaseReference
    lateinit var mDatabase4 : DatabaseReference
    lateinit var mProgressbar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_verification)
        toolbar18.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })
        hideandshowdetails.visibility=View.INVISIBLE

        verifyPuser.setOnClickListener {
            val userPhonoNo=getuserphonenumber.text.toString()

            mProgressbar= ProgressDialog(this)
            mProgressbar.setMessage("Loading...")
            mProgressbar.setCanceledOnTouchOutside(false)
            mProgressbar.show()

            mDatabase4=FirebaseDatabase.getInstance().getReference("PR")
            mDatabase=FirebaseDatabase.getInstance().getReference("Convert")

            mDatabase.addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot){
                    if(dataSnapshot.child(userPhonoNo).exists()){
                        var uid=dataSnapshot.child(userPhonoNo).getValue(String::class.java)

                        mDatabase3= FirebaseDatabase.getInstance().getReference("user")
                        mDatabase3.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                val username=dataSnapshot.child(uid).child("Name").getValue(String::class.java)
                                val email=dataSnapshot.child(uid).child("Email").getValue(String::class.java)
                                val phonenumber=dataSnapshot.child(uid).child("Phone_Number").getValue(String::class.java)
                                val collegename=dataSnapshot.child(uid).child("College_Name").getValue(String::class.java)

                                //fetch value from user
                                mDatabase2=FirebaseDatabase.getInstance().getReference("PR")
                                mDatabase2.addListenerForSingleValueEvent(object:ValueEventListener{
                                    override fun onDataChange(dataSnapshot: DataSnapshot){
                                        hideandshowdetails.visibility=View.VISIBLE
                                        if(dataSnapshot.child("Participated").child(uid).exists()){
                                            vname.text=username.toString()
                                            vemail.text=email.toString()
                                            vphone.text=phonenumber.toString()
                                            vcollege.text=collegename.toString()
                                            vparticipated.text="Yes"
                                            mProgressbar.dismiss()
                                        }
                                        else{
                                            vname.text=username.toString()
                                            vemail.text=email.toString()
                                            vphone.text=phonenumber.toString()
                                            vcollege.text=collegename.toString()
                                            vparticipated.text="No"
                                            mProgressbar.dismiss()
                                        }
                                    }
                                    override fun onCancelled(error:DatabaseError) {
                                        Toast.makeText(this@UsersVerification,"Error to connect database.",Toast.LENGTH_SHORT).show()
                                    }
                                })
                            }
                            override fun onCancelled(p0: DatabaseError?) {
                            }
                        })
                    }
                    else{
                        Toast.makeText(this@UsersVerification,"Account does not exists.",Toast.LENGTH_SHORT).show()
                        mProgressbar.dismiss()
                    }
                }
                override fun onCancelled(error:DatabaseError) {
                    Toast.makeText(this@UsersVerification,"Error to connect database.",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
