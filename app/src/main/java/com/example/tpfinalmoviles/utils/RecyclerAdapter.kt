package com.example.tpfinalmoviles.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tpfinalmoviles.R

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    val titles = arrayOf("Apple", "Samsung", "Xiaomi", "Huawei", "Huawei", "Huawei", "Huawei", "Huawei");
    val details = arrayOf("Apple Company", "Samsung Company", "Xiaomi company", "Huawei company", "Huawei", "Huawei", "Huawei", "Huawei")

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.recycler_card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemDetails.text = details[i]
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView
        var itemDetails: TextView

        init {
            itemTitle = itemView.findViewById(R.id.titleField)
            itemDetails = itemView.findViewById(R.id.detailsField)
        }
    }

}