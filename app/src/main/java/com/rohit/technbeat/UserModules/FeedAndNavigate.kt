package com.rohit.technbeat.UserModules

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.rohit.technbeat.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.rohit.technbeat.*
import com.rohit.technbeat.Admin.Admin.UploadInfo
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_feed_and_navigate.*
import kotlinx.android.synthetic.main.app_bar_feed_and_navigate.*
import kotlinx.android.synthetic.main.content_feed_and_navigate.*
import kotlinx.android.synthetic.main.item_image.view.*
import kotlinx.android.synthetic.main.nav_header_feed_and_navigate.*
import com.squareup.picasso.Callback


class FeedAndNavigate : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference
    lateinit var mdatabase: DatabaseReference
    lateinit var mProgressbar: ProgressDialog
    var data:ArrayList<UploadInfo> = ArrayList<UploadInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_and_navigate)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        onStart()

        mdatabase= FirebaseDatabase.getInstance().getReference("images")
        val option = FirebaseRecyclerOptions.Builder<UploadInfo>()
                .setQuery(mdatabase, UploadInfo::class.java)
                .build()

        val adapter=object: FirebaseRecyclerAdapter<UploadInfo, ViewHolder>(option)
        {
            override fun getItemCount(): Int {
                return super.getItemCount()
            }
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder(
                        LayoutInflater.from(parent.context)
                                .inflate(R.layout.item_image, parent, false))
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int, model: UploadInfo) {
                holder.ViewHolder2(model,applicationContext)
            }
        }

        adapter.startListening()
        imgrec.layoutManager= LinearLayoutManager(this)
        imgrec.adapter=adapter


        nav_view.setNavigationItemSelectedListener(this)
    }


    public class ViewHolder(var view: View): RecyclerView.ViewHolder(view)
    {
        public fun ViewHolder2(imgData: UploadInfo, context: Context) {
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

    override fun onStart() {
        super.onStart()
        val uid=mAuth.currentUser?.uid
        mDatabase= FirebaseDatabase.getInstance().getReference("user").child(uid)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val Dname = dataSnapshot.child("Name").getValue(String::class.java)
                val DEmail = dataSnapshot.child("Email").getValue(String::class.java)
                    display_userName.text = Dname + " "
                    display_EmailID.text = DEmail + " "
            }
            override fun onCancelled(p0: DatabaseError?) {
            }
        })

    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.feed_and_navigate, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.about_fest -> {
                val intent= Intent(this, AboutFest::class.java)
                startActivity(intent)
            }
            R.id.core -> {
                val intent= Intent(this, CoreMembers::class.java)
                startActivity(intent)
            }
            R.id.event -> {
                val intent= Intent(this, Events::class.java)
                startActivity(intent)
            }
            R.id.participated -> {
                val intent= Intent(this, Participated::class.java)
                startActivity(intent)
            }
            R.id.sponsor -> {
                val intent= Intent(this, Sponsors::class.java)
                startActivity(intent)
            }
            R.id.follow -> {
                val intent= Intent(this, FollowUs::class.java)
                startActivity(intent)
            }
            R.id.admin -> {
                val intent= Intent(this, menu::class.java)
                startActivity(intent)
            }
            R.id.developer -> {
                val intent= Intent(this, Developer::class.java)
                startActivity(intent)
            }
            R.id.Logout -> {
                val token=getSharedPreferences("UserEmail", Context.MODE_PRIVATE)
                var editor=token.edit()
                editor.putString("UserEmail","")
                editor.commit()
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}
