package com.example.seonsijo.main.alarm

import android.annotation.SuppressLint
import android.graphics.Insets.add
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seonsijo.R
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivityAlarmBinding
import com.example.seonsijo.main.alarm.adapter.AlarmAdapter
import com.example.seonsijo.main.alarm.adapter.AlarmSwipeHelper

class AlarmActivity : BaseActivity<ActivityAlarmBinding>(R.layout.activity_alarm){

    private val alarmList: ArrayList<AlarmData> = ArrayList()

    private val alarmAdapter : AlarmAdapter by lazy {
        AlarmAdapter(alarmList,this)
    }

    override fun initView() {

        alarmList.add(AlarmData(1,"과학","과학 알림이 새로 왔어요 ㅎㅎ"))

        binding.run {

            alarmRv.run {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                adapter = alarmAdapter
            }

            val swipeHelper = AlarmSwipeHelper(alarmAdapter).apply {
                setClamp(resources.displayMetrics.widthPixels.toFloat()/7)
            }
            ItemTouchHelper(swipeHelper).attachToRecyclerView(alarmRv)

            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    override fun observeEvent() {

    }

    fun removeItem(position: Int){
        alarmList.removeAt(position)
    }
}