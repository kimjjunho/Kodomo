package com.example.seonsijo.main

import androidx.activity.viewModels
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.schedule.ScheduleParam
import com.example.domain.entity.signup.SignUpEntity
import com.example.seonsijo.main.alarm.AlarmActivity
import com.example.seonsijo.util.MyApplication
import com.example.seonsijo.R
import com.example.seonsijo.test.TestListActivity
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.util.repeatOnStarted
import com.example.seonsijo.databinding.ActivityMainBinding
import com.example.seonsijo.signup.SignUpViewModel
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Date
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(): BaseActivity<ActivityMainBinding>
    (R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()

    private val date: String = SimpleDateFormat("yyyyMMdd").format(Date(System.currentTimeMillis()))

    override fun initView() {
        classTextChange()

        gradeTextChange()

        useRecyclerView()

        tableClickEvent()

        mainViewModel.autoSchedule()

        binding.run {
            tvGrade.text = gradeNum.toString() + "학년"

            tvClass.text = classNum.toString() + "반"

            btnBeforeWeek.setOnClickListener {}

            btnAfterWeek.setOnClickListener {}

            btnAlarm.setOnClickListener {
                startActivity(Intent(this@MainActivity, AlarmActivity::class.java))
            }
        }
    }

    companion object SignUpVariable {
        var gradeNum = 0
        var classNum = 0
        var device_token: String? = null
    }

    private fun tableClickEvent(){
        binding.run {
            monday1.gotoT()
            monday2.gotoT()
            monday3.gotoT()
            monday4.gotoT()
            monday5.gotoT()
            monday6.gotoT()
            monday7.gotoT()

            tuesday1.gotoT()
            tuesday2.gotoT()
            tuesday3.gotoT()
            tuesday4.gotoT()
            tuesday5.gotoT()
            tuesday6.gotoT()
            tuesday7.gotoT()

            wednesday1.gotoT()
            wednesday2.gotoT()
            wednesday3.gotoT()
            wednesday4.gotoT()
            wednesday5.gotoT()
            wednesday6.gotoT()
            wednesday7.gotoT()

            thursday1.gotoT()
            thursday2.gotoT()
            thursday3.gotoT()
            thursday4.gotoT()
            thursday5.gotoT()
            thursday6.gotoT()
            thursday7.gotoT()

            friday1.gotoT()
            friday2.gotoT()
            friday3.gotoT()
            friday4.gotoT()
            friday5.gotoT()
            friday6.gotoT()
            friday7.gotoT()
        }
    }

    private fun classTextChange(){
        binding.tvClass.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                mainViewModel.getSchedule(
                    ScheduleParam(
                        grade = gradeNum,
                        class_num = classNum,
                        date = date
                    )
                )

                signUpViewModel.updateSignUpVariable(
                    SignUpEntity(
                        grade = gradeNum,
                        class_num = classNum,
                        device_token = device_token
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

                mainViewModel.getSchedule(
                    ScheduleParam(
                        grade = gradeNum,
                        class_num = classNum,
                        date = date
                    )
                )

                signUpViewModel.updateSignUpVariable(
                    SignUpEntity(
                        grade = gradeNum,
                        class_num = classNum,
                        device_token = device_token
                    )
                )
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun TextView.gotoT() {
        setOnClickListener {
            if(!this.text.isNullOrEmpty()) {
                gotoTestListActivity(this)
            }
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
                    else -> showToastShort("알 수 없는 오류")
                }
            }
        }
    }
}