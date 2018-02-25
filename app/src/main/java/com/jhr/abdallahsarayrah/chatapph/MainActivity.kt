package com.jhr.abdallahsarayrah.chatapph

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val msgList = ArrayList<Message>()
    private var mNotificationManager: NotificationManager? = null

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebaseVar = FirebaseDatabase.getInstance().reference
        firebaseVar.child("chatApp").child("me").addValueEventListener(
                object : ValueEventListener, ChildEventListener {
                    override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
                    }

                    override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
                    }

                    override fun onChildAdded(p0: DataSnapshot?, p1: String?) {

                    }

                    override fun onChildRemoved(p0: DataSnapshot?) {
                    }

                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(p0: DataSnapshot?) {
                        fillAdp(p0!!.key.toString(), p0.value.toString())
                        notifyMsg(p0.value.toString())
                    }
                }
        )
        firebaseVar.child("chatApp").child("him").addValueEventListener(
                object : ValueEventListener, ChildEventListener {
                    override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
                    }

                    override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
                    }

                    override fun onChildAdded(p0: DataSnapshot?, p1: String?) {

                    }

                    override fun onChildRemoved(p0: DataSnapshot?) {
                    }

                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    @RequiresApi(Build.VERSION_CODES.N)
                    @SuppressLint("PrivateResource")
                    override fun onDataChange(p0: DataSnapshot?) {
                        fillAdp(p0!!.key.toString(), p0.value.toString())
                        notifyMsg(p0.value.toString())
                    }
                }
        )

        fillAdp()
    }

    @SuppressLint("Recycle")
    private fun fillAdp(name: String = "", msg: String = "") {
        msgList.clear()

        val obj = ChatDB(this)
        val db = obj.writableDatabase
        if (name.isNotEmpty() and msg.isNotEmpty()) {
            db.execSQL("insert into chat values(\"$name\", \"$msg\")")
        }
        val cursor = db.rawQuery("select * from chat", null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            msgList.add(Message(cursor.getString(0), cursor.getString(1)))
            cursor.moveToNext()
        }

        val adp = MessagesAdp(this, msgList)
        recyclerView.adapter = adp
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.scrollToPosition(msgList.size - 1)
    }

    private fun notifyMsg(value: String) {
        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.msg_icon)
                .setContentTitle("My notification")
                .setContentText(value)

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        mNotificationManager.notify(1, mBuilder.build())
    }
}
