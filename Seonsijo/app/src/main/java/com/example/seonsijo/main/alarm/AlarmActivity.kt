package com.example.seonsijo.main.alarm

import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.alarm.GetAlarmParam
import com.example.seonsijo.R
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivityAlarmBinding
import com.example.seonsijo.main.alarm.adapter.AlarmAdapter
import com.example.seonsijo.main.alarm.adapter.AlarmSwipeHelper
import com.example.seonsijo.util.classNum
import com.example.seonsijo.util.gradeNum
import com.example.seonsijo.util.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AlarmActivity : BaseActivity<ActivityAlarmBinding>(R.layout.activity_alarm){

    private val alarmViewModel: AlarmViewModel by viewModels()

    private val alarmList: ArrayList<AlarmData> = ArrayList()

    private val alarmAdapter : AlarmAdapter by lazy {
        AlarmAdapter(alarmList,this, alarmViewModel)
    }

    override fun initView() {
        alarmViewModel.getAlarm(GetAlarmParam(gradeNum,classNum))

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
        repeatOnStarted {
            alarmViewModel.run {
                deleteSuccess.observe(this@AlarmActivity){
                    alarmList.removeAt(it)
                    alarmAdapter.notifyItemChanged(it)
                }
                eventFlow.collect{
                    when(it){

                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        alarmViewModel.getAlarm(GetAlarmParam(gradeNum,classNum))
    }
}