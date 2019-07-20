package com.rohit.technbeat.Admin.Admin

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import com.rohit.technbeat.*
import kotlinx.android.synthetic.main.activity_adminmenu.*

class AdminMenu: AppCompatActivity() {

    private val TAG = "StorageActivity"
    //track Choosing Image Intent
    private val CHOOSING_IMAGE_REQUEST = 1234

    private var fileUri: Uri? = null

    private var dataReference: DatabaseReference? = null
    private var imageReference: StorageReference? = null
    private var fileRef: StorageReference? = null

    private var mAdapter: FirebaseRecyclerAdapter<UploadInfo, ImgViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminmenu)
        toolbar30.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })

        sponsorsupload.setOnClickListener {
            val intent=Intent(this, S_upload::class.java)
            startActivity(intent)
        }

        newsupload.setOnClickListener {
            val intent=Intent(this, Storage::class.java)
            startActivity(intent)
        }
        userverification.setOnClickListener {
            val intent=Intent(this, UsersVerification::class.java)
            startActivity(intent)
        }

    }
}