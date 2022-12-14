package com.example.seonsijo.main.alarm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.seonsijo.R
import com.example.seonsijo.main.alarm.AlarmActivity
import com.example.seonsijo.main.alarm.AlarmData
import com.example.seonsijo.main.alarm.AlarmViewModel
import java.util.*
import kotlin.collections.ArrayList

class AlarmAdapter(
    private val alarmList: ArrayList<AlarmData>,
    private val alarmActivity: AlarmActivity,
    private val alarmViewModel: AlarmViewModel
): RecyclerView.Adapter<AlarmAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val alarmView = LayoutInflater.from(parent.context).inflate(R.layout.item_test_list,parent,false)
        return CustomViewHolder(alarmView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.tvItemTile.text = alarmList[position].subject
        holder.tvItemName.text = alarmList[position].content

        holder.btnItemDelete.setOnClickListener {
            alarmViewModel.deleteAlarm(alarmList[position].alarm_id, position)
        }
    }

    override fun getItemCount(): Int = alarmList.size

    fun swapData(fromPos: Int, toPos: Int) {
        val list = alarmList.toMutableList()
        Collections.swap(list, fromPos, toPos)
        notifyItemMoved(fromPos, toPos)
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvItemTile: TextView = itemView.findViewById(R.id.tv_item_title)
        val tvItemName: TextView = itemView.findViewById(R.id.tv_item_name)
        val btnItemDelete: Button = itemView.findViewById(R.id.btn_item_delete)
    }
}