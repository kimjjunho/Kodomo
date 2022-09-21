package com.example.seonsijo.main.alarm

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seonsijo.R
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivityAlarmBinding
import com.example.seonsijo.main.alarm.adapter.AlarmAdapter

class AlarmActivity : BaseActivity<ActivityAlarmBinding>(R.layout.activity_alarm){

    private lateinit var alarmList: List<AlarmData>

    override fun initView() {

        alarmList = listOf(AlarmData(1,"과학","과학 알림이 새로 왔어요 ㅎㅎ"))

        binding.run {

            alarmRv.run {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                adapter = AlarmAdapter(alarmList)
            }

            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    override fun observeEvent() {

    }
}