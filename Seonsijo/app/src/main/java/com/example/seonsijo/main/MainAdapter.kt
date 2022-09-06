package com.example.seonsijo.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.seonsijo.R

class MainAdapter(
    private val mainList: List<String>,
    private val mainActivity: MainActivity,
    private val isGrade: Boolean
): RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val mainView = LayoutInflater.from(parent.context).inflate(R.layout.item_main,parent,false)
        return CustomViewHolder(mainView, mainActivity, isGrade)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.tvItem.text = mainList[position]

        holder.apply { bind(position) }
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    class CustomViewHolder(
        itemView: View,
        mainActivity: MainActivity,
        isGrade: Boolean
    ): RecyclerView.ViewHolder(itemView) {
        val tvItem: TextView = itemView.findViewById(R.id.tv_item)
        private val itemAll: ConstraintLayout = itemView.findViewById(R.id.item_all)
        private val itemLine: View = itemView.findViewById(R.id.item_line)

        private val tvGrade: TextView = mainActivity.findViewById(R.id.tv_grade)
        private val tvClass: TextView = mainActivity.findViewById(R.id.tv_class)

        private val isGrade = isGrade

        fun bind(
            position: Int
        ){
            if(position == 0){
                itemLine.visibility = View.INVISIBLE
            }

            itemAll.setOnClickListener {
                if(isGrade){
                    tvGrade.text = tvItem.text
                }else{
                    tvClass.text = tvItem.text
                }
            }
        }
    }

}