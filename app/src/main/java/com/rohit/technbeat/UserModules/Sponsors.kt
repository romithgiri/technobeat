package com.rohit.technbeat.UserModules

import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rohit.technbeat.Admin.Admin.UploadInfo
import com.rohit.technbeat.R
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_sponsors.*
import kotlinx.android.synthetic.main.item_image.view.*

class Sponsors: AppCompatActivity() {
    lateinit var mDatabase : DatabaseReference
    lateinit var mdatabase: DatabaseReference
    lateinit var mProgressbar: ProgressDialog
    var data:ArrayList<UploadInfo> = ArrayList<UploadInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sponsors)

        toolbar11.setTitle("Our Sponsors")
        toolbar11.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })


        mdatabase= FirebaseDatabase.getInstance().getReference("Sponsors")
        val option = FirebaseRecyclerOptions.Builder<UploadInfo>()
                .setQuery(mdatabase, UploadInfo::class.java)
                .build()

        val adapter=object: FirebaseRecyclerAdapter<UploadInfo, ViewHolders>(option)
        {
            override fun getItemCount(): Int {
                return super.getItemCount()
            }
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolders {
                return ViewHolders(
                        LayoutInflater.from(parent.context)
                                .inflate(R.layout.item_image, parent, false))
            }

            override fun onBindViewHolder(holder: ViewHolders, position: Int, model: UploadInfo) {
                holder.ViewHolders2(model,applicationContext)
            }
        }

        adapter.startListening()
        imgrecspon.layoutManager= LinearLayoutManager(this)
        imgrecspon.adapter=adapter
    }
    public class ViewHolders(var view: View): RecyclerView.ViewHolder(view)
    {
        public fun ViewHolders2(imgData: UploadInfo, context: Context) {
            Picasso.with(context).setIndicatorsEnabled(false)
            Picasso.with(context)
                    .load(imgData.url)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .placeholder(R.drawable.loadimg)
                    .resize(1080,1080)
                    .into(view.imgView, object : Callback {
                        override fun onSuccess() {

                        }

                        override fun onError() {
                            //Try again online if cache failed
                            Picasso.with(context)
                                    .load(imgData.url)
                                    .error(R.drawable.error)
                                    .placeholder(R.drawable.loadimg)
                                    .resize(1080,1080)
                                    .into(view.imgView)
                        }
                    })
        }
    }

}