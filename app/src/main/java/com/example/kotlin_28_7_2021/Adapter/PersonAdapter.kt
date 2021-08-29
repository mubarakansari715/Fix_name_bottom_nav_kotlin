package com.example.kotlin_28_7_2021.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_28_7_2021.ModelClass.PersonModelClass
import com.example.kotlin_28_7_2021.R

class PersonAdapter(val context: Context, val viewList: List<PersonModelClass>) :

    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.persondetails, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: PersonModelClass = viewList[position]
        holder.name.text = "Name : " + model.name
        holder.username.text = "Username : ${model.username}"
        holder.email.text = "Email : ${model.email}"
        holder.phone.text = "Phone ${model.phone}"
    }

    override fun getItemCount(): Int {
        return viewList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val username = itemView.findViewById<TextView>(R.id.tv_username)
        val email = itemView.findViewById<TextView>(R.id.tv_email)
        val phone = itemView.findViewById<TextView>(R.id.tv_phone)

    }
}