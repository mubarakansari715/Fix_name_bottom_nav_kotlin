package com.example.kotlin_28_7_2021.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_28_7_2021.ModelClass.RecycelViewModelClass
import com.example.kotlin_28_7_2021.R
import com.squareup.picasso.Picasso

class RecycelViewAdapters(val context: Context, val viewList: List<RecycelViewModelClass>) :
    RecyclerView.Adapter<RecycelViewAdapters.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_recycalview, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model: RecycelViewModelClass = viewList[position]
        holder.title.text = viewList[position].title
        Picasso.get().load(model.url).fit().centerInside().into(holder.img);
        //Glide.with(context). load(viewList[position].url +".jpeg").into(holder.img)

    }

    override fun getItemCount(): Int {
        return viewList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val img = itemView.findViewById<ImageView>(R.id.tv_imageView)
    }

}