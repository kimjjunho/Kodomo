package com.example.seonsijo.main

import androidx.activity.viewModels
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.ScheduleRequestEntity
import com.example.seonsijo.main.alarm.AlarmActivity
import com.example.seonsijo.util.MyApplication
import com.example.seonsijo.R
import com.example.seonsijo.test.TestListActivity
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.util.repeatOnStarted
import com.example.seonsijo.util.classNum
import com.example.seonsijo.databinding.ActivityMainBinding
import com.example.seonsijo.util.gradeNum
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Date
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(): BaseActivity<ActivityMainBinding>
    (R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModels()

    private val date: String = SimpleDateFormat("yyyyMMdd").format(Date(System.currentTimeMillis()))

    override fun initView() {

        classTextChange()

        gradeTextChange()

        useRecyclerView()

        tableClickEvent()

        binding.run {

            MyApplication.prefs.setBoolean("check",true)

            tvGrade.text = gradeNum.toString() + "학년"
            tvClass.text = classNum.toString() + "반"

            MyApplication.prefs.setString("classNum", classNum.toString())
            MyApplication.prefs.setString("gradeNum", gradeNum.toString())

            btnBeforeWeek.setOnClickListener {}

            btnAfterWeek.setOnClickListener {}

            btnAlarm.setOnClickListener {
                startActivity(Intent(this@MainActivity, AlarmActivity::class.java))
            }
        }
    }

    private fun tableClickEvent(){
        binding.run {
            gotoT(btnMonday1, monday1)
            gotoT(btnMonday2, monday2)
            gotoT(btnMonday3, monday3)
            gotoT(btnMonday4, monday4)
            gotoT(btnMonday5, monday5)
            gotoT(btnMonday6, monday6)
            gotoT(btnMonday7, monday7)


            gotoT(btnTuesday1, tuesday1)
            gotoT(btnTuesday2, tuesday2)
            gotoT(btnTuesday3, tuesday3)
            gotoT(btnTuesday4, tuesday4)
            gotoT(btnTuesday5, tuesday5)
            gotoT(btnTuesday6, tuesday6)
            gotoT(btnTuesday7, tuesday7)


            gotoT(btnWednesday1, wednesday1)
            gotoT(btnWednesday2, wednesday2)
            gotoT(btnWednesday3, wednesday3)
            gotoT(btnWednesday4, wednesday4)
            gotoT(btnWednesday5, wednesday5)
            gotoT(btnWednesday6, wednesday6)
            gotoT(btnWednesday7, wednesday7)


            gotoT(btnThursday1, thursday1)
            gotoT(btnThursday2, thursday2)
            gotoT(btnThursday3, thursday3)
            gotoT(btnThursday4, thursday4)
            gotoT(btnThursday5, thursday5)
            gotoT(btnThursday6, thursday6)
            gotoT(btnThursday7, thursday7)


            gotoT(btnFriday1, friday1)
            gotoT(btnFriday2, friday2)
            gotoT(btnFriday3, friday3)
            gotoT(btnFriday4, friday4)
            gotoT(btnFriday5, friday5)
            gotoT(btnFriday6, friday6)
            gotoT(btnFriday7, friday7)

        }
    }

    private fun classTextChange(){
        binding.tvClass.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                classNum = binding.tvClass.text.toString()[0].toString().toInt()
                MyApplication.prefs.setString("classNum", classNum.toString())

                mainViewModel.getSchedule(
                    ScheduleRequestEntity(
                        grade = gradeNum,
                        class_num = classNum,
                        date = date
                    )
                )
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun gradeTextChange(){
        binding.tvGrade.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                gradeNum = binding.tvGrade.text.toString()[0].toString().toInt()
                MyApplication.prefs.setString("gradeNum", gradeNum.toString())

                mainViewModel.getSchedule(
                    ScheduleRequestEntity(
                        grade = gradeNum,
                        class_num = classNum,
                        date = date
                    )
                )
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun gotoT(button: Button, textView: TextView){
        button.setOnClickListener {
            gotoTestListActivity(textView)
        }
    }

    private fun gotoTestListActivity(textView: TextView){
        val intent = Intent(applicationContext, TestListActivity::class.java)
        intent.putExtra("subject",textView.text.toString())

        startActivity(intent)
    }

    private fun useRecyclerView(){
        binding.run {
            btnGradeNum.setOnClickListener {
                slidingPanelLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

                slidingPanelRecyclerview.apply {
                    adapter = MainAdapter(listOf("1학년","2학년","3학년"),this@MainActivity,true)
                    layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                    setHasFixedSize(true)
                }
            }

            btnClassNum.setOnClickListener {
                slidingPanelLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

                slidingPanelRecyclerview.apply {
                    adapter = MainAdapter(listOf("1반","2반","3반","4반"),this@MainActivity,false)
                    layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                    setHasFixedSize(true)
                }
            }
        }
    }

    override fun observeEvent() {
        repeatOnStarted {
            mainViewModel.eventFlow.collect{
                when(it){
                    is MainViewModel.Event.Success -> {
                        showToastShort("성공")
                    }
                    is MainViewModel.Event.BadRequest -> {
                        showToastShort("학년, 반이 잘 적용되었는지 확인해주세요")
                    }
                    is MainViewModel.Event.Forbidden -> {
                        showToastShort("접근 불가 권한입니다")
                    }
                    is MainViewModel.Event.NotFound -> {
                        showToastShort("데이터를 찾을 수 없습니다")
                    }
                    is MainViewModel.Event.Server -> {
                        showToastShort("서버가 닫혀 있습니다")
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }
}