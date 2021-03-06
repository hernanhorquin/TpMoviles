package com.example.tpfinalmoviles.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tpfinalmoviles.R
import com.example.tpfinalmoviles.data.model.CowFiredAlert
import kotlinx.android.synthetic.main.recycler_card_layout.view.*

class RecyclerAdapter : RecyclerView.Adapter<ViewHolder>() {

    var cowFiredAlertList =
        mutableListOf<CowFiredAlert>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(cowFiredAlertList[i])
    }

    override fun getItemCount(): Int = cowFiredAlertList.size
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: CowFiredAlert) = with(itemView) {
        recyclerCardIdData.text = item.id.toString()
        recyclerCardCowDataId.text = item.cow.id.toString()
        recyclerCardBcsData.text = item.bcsFired.toString()
        recyclerCardDateData.text = item.fecha.subSequence(0,10)
    }
}