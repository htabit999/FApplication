package com.example.fbapplication

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fbapplication.models.Usr
import com.squareup.picasso.Picasso

class UserAdapter (var context: Context, var userList : ArrayList<Usr>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.user_layout,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser=userList[position]
        holder.textName.text=currentUser.NOM
        Picasso.get().load(userList[position].URL).into(holder.icon)
        holder.itemView.setOnClickListener {
            //Log.d(ContentValues.TAG, "1 " + currentUser.NOM)
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name", currentUser.NOM)
            intent.putExtra("uid", currentUser.UID)
            intent.putExtra("url", currentUser.URL)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ReceiveViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {}

    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val textName=itemView.findViewById<TextView>(R.id.text_name)
        val icon=itemView.findViewById<ImageView>(R.id.icon)
    }

}