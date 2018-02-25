package com.jhr.abdallahsarayrah.chatapph

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by abdallah.sarayrah on 1/17/2018.
 */

class MessagesAdp(private var context: Context, private var list: ArrayList<Message>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (list[position].name == "me") 1
        else 2

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return if (viewType == 1) {
            val v = LayoutInflater.from(context).inflate(R.layout.sender_row, parent,
                    false)
            MessageViewS(v)
        } else {
            val v = LayoutInflater.from(context).inflate(R.layout.receiver_row, parent,
                    false)
            MessageViewR(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder!!.itemViewType == 1) (holder as MessageViewS).bind(list[position].msg)
        else (holder as MessageViewR).bind(list[position].msg)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MessageViewS(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewMsgId = itemView.findViewById<TextView>(R.id.textViewMsg)
        fun bind(msg: String) {
            textViewMsgId.text = msg
        }
    }

    class MessageViewR(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewMsgId = itemView.findViewById<TextView>(R.id.textViewMsg)
        fun bind(msg: String) {
            textViewMsgId.text = msg
        }
    }
}
