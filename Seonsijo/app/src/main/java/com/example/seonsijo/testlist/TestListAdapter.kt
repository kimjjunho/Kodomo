package com.example.seonsijo.testlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.seonsijo.R

class TestListAdapter(
    private val testList: List<TestListData>,
    private val testListActivity: TestListActivity
    ) : RecyclerView.Adapter<TestListAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val listView = LayoutInflater.from(parent.context).inflate(R.layout.item_test_list,parent,false)
        return CustomViewHolder(listView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.tvItemTitle.text = testList[position].title
        holder.tvItemName.text = testList[position].name

        holder.itemConstraintLayout.setOnClickListener {
            testListActivity.gotoTestActivity(holder.tvItemTitle.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return testList.size
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvItemTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val tvItemName: TextView = itemView.findViewById(R.id.tv_item_name)
        val itemConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.item_constraint_layout)

    }
}