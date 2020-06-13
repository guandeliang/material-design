/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-20 下午9:41
 *
 */
package com.jacob.material.applaunch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jacob.http.MdGlide
import com.jacob.http.MdRetrofitFactory
import java.util.*

class ThronesAdapter(private val context: Context) : RecyclerView.Adapter<ThronesAdapter.ThronesHolder>() {
    private val dataList: MutableList<Thrones>
    fun setDataList(dataList: List<Thrones>?) {
        this.dataList.clear()
        this.dataList.addAll(dataList!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun getItem(position: Int): Thrones {
        return dataList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThronesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_holder, parent, false)
        return ThronesHolder(view)
    }

    override fun onBindViewHolder(holder: ThronesHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    inner class ThronesHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        fun bind(thrones: Thrones) {
            val imageView = itemView.findViewById<ImageView>(R.id.image_view)
            val titleTextView = itemView.findViewById<TextView>(R.id.title_text_view)
            val summaryTextView = itemView.findViewById<TextView>(R.id.summary_text_view)
            val url = MdRetrofitFactory.BASE_URL + "images/" + thrones.fileName
            MdGlide.with(context).load(url).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView)
            titleTextView.text = thrones.title
            summaryTextView.text = thrones.summary
        }
    }

    init {
        dataList = ArrayList()
    }
}