package com.example.tpfinalmoviles.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tpfinalmoviles.R
import com.example.tpfinalmoviles.data.model.CowFiredAlert
import kotlinx.android.synthetic.main.recycler_card_layout.view.*

class HerdFiredAlertsRecycler : RecyclerView.Adapter<HerdFiredAlertHolder>() {

    var herdFiredAlertList =
        mutableListOf<CowFiredAlert>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HerdFiredAlertHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_card_layout, viewGroup, false)
        return HerdFiredAlertHolder(v)
    }

    override fun onBindViewHolder(viewHolder: HerdFiredAlertHolder, i: Int) {
        viewHolder.bind(herdFiredAlertList[i])
    }

    override fun getItemCount(): Int = herdFiredAlertList.size
}

class HerdFiredAlertHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: CowFiredAlert) = with(itemView) {
        recyclerCardCowField.text = "RODEO"
        recyclerCardIdData.text = item.id.toString()
        recyclerCardCowDataId.text = item.cow.id.toString()
        recyclerCardBcsData.text = item.bcsFired.toString()
        recyclerCardDateData.text = item.fecha.subSequence(0,10)
    }
}