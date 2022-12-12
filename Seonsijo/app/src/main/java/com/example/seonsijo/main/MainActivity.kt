package com.example.seonsijo.main

import android.annotation.SuppressLint
import androidx.activity.viewModels
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.schedule.ScheduleEntity
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
import java.time.DayOfWeek
import java.time.Year
import java.util.Calendar
import java.util.GregorianCalendar
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.min

@AndroidEntryPoint
class MainActivity @Inject constructor(): BaseActivity<ActivityMainBinding>
    (R.layout.activity_main) {

    private var toastMsg = ""
    private val mainViewModel: MainViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()

    private var startAt = ""
    private var endAt = ""
    private var year = 0
    private var month = 0
    private var day = 0
    private var checkDay = 0

    private val calendar = GregorianCalendar()

    @SuppressLint("SimpleDateFormat")
    private val date: String = SimpleDateFormat("yyyyMMdd").format(Date(System.currentTimeMillis()))

    @SuppressLint("SetTextI18n")
    override fun initView() {
        classTextChange()

        gradeTextChange()

        useRecyclerView()

        // tableClickEvent()

        year = calendar.get(Calendar.YEAR)
        month = (calendar.get(Calendar.MONTH)+1)
        day = calendar.get(Calendar.DATE)

        Log.d("TAG", "initView: "+year)
        Log.d("TAG", "initView: "+month)
        Log.d("TAG", "initView: "+day)


        when (calendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> {
                startAt = startMapper(0)
                endAt = endMapper(5)
            }
            2 -> {
                startAt = startMapper(0)
                endAt = endMapper(4)
            }
            3 -> {
                startAt = startMapper(1)
                endAt = endMapper(3)
            }
            4 -> {
                startAt = startMapper(2)
                endAt = endMapper(2)
            }
            5 -> {
                startAt = startMapper(3)
                endAt = endMapper(1)
            }
            6 -> {
                startAt = startMapper(4)
                endAt = endMapper(0)
            }
            7 -> {
                startAt = startMapper(0)
                endAt = endMapper(6)
            }
        }

        binding.run {

            tvGrade.text = gradeNum.toString() + "학년"

            tvClass.text = classNum.toString() + "반"

            btnBeforeWeek.setOnClickListener {
                checkDay --

                if (checkDay == 0) {
                    tvScheduleWeek.text = "이번주 시간표"
                } else {
                    if (checkDay < 0) {
                        tvScheduleWeek.text = abs(checkDay).toString() + "주 전 시간표"
                    } else {
                        tvScheduleWeek.text = checkDay.toString() + "주 후 시간표"
                    }
                }

                startAt = startMapper(0)
                endAt = endMapper(0)

                mainViewModel.getSchedule(
                    ScheduleParam(
                        grade = gradeNum,
                        class_num = classNum,
                        startAt = startAt,
                        endAt = endAt,
                    )
                )
            }

            btnAfterWeek.setOnClickListener {
                checkDay ++
                if (checkDay == 0) {
                    tvScheduleWeek.text = "이번주 시간표"
                } else {
                    if (checkDay < 0) {
                        tvScheduleWeek.text = abs(checkDay).toString() + "주 전 시간표"
                    } else {
                        tvScheduleWeek.text = checkDay.toString() + "주 후 시간표"
                    }
                }

                startAt = startMapper(0)
                endAt = endMapper(0)

                mainViewModel.getSchedule(
                    ScheduleParam(
                        grade = gradeNum,
                        class_num = classNum,
                        startAt = startAt,
                        endAt = endAt,
                    )
                )
            }

            btnAlarm.setOnClickListener {
                startActivity(Intent(this@MainActivity, AlarmActivity::class.java))
            }
        }
    }

    companion object SignUpVariable {
        var gradeNum = 1
        var classNum = 1
        var device_token: String? = null
    }

    private fun dateMapper(year: Int, month: Int, day: Int): String =
        year.toString() + "-" + String.format("%02d", month) + "-" + String.format("%02d", day)

    private fun startMapper(minusDay: Int): String {
        val calendar = GregorianCalendar(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE) + 7 * checkDay - minusDay
        )

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DATE)

        return dateMapper(year, month, day)
    }


    private fun endMapper(plusDay: Int): String {
        val calendar = GregorianCalendar(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE) + 7 * checkDay + plusDay
        )

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DATE)

        return dateMapper(year, month, day)
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

//                mainViewModel.getSchedule(
//                    ScheduleParam(
//                        grade = gradeNum,
//                        class_num = classNum,
//                        startAt = "2022-12-12",
//                        endAt = "2022-12-16"
//                    )
//                )
//
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

//                mainViewModel.getSchedule(
//                    ScheduleParam(
//                        grade = gradeNum,
//                        class_num = classNum,
//                        startAt = "2022",
//                    )
//                )
//
                signUpViewModel.updateSignUpVariable(
                    SignUpEntity(
                        grade = gradeNum,
                        class_num = classNum,
                        device_token = device_token
                    )
                )

                mainViewModel.getSchedule(
                    ScheduleParam(
                        grade = gradeNum,
                        class_num = classNum,
                        startAt = startAt,
                        endAt = endAt,
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

    private fun fetchMonday(mondayList: List<ScheduleEntity.Schedule>) {
        binding.run {
            try {
                monday1.text = mondayList[0].subject
                monday2.text = mondayList[1].subject
                monday3.text = mondayList[2].subject
                monday4.text = mondayList[3].subject
                monday5.text = mondayList[4].subject
                monday6.text = mondayList[5].subject
                monday7.text = mondayList[6].subject
            } catch (e: java.lang.Exception) {
                toastMsg += "월, "
            }

        }
    }

    private fun fetchTuesday(tuesdayList: List<ScheduleEntity.Schedule>) {
        binding.run {
            try {
                tuesday1.text = tuesdayList[0].subject
                tuesday2.text = tuesdayList[1].subject
                tuesday3.text = tuesdayList[2].subject
                tuesday4.text = tuesdayList[3].subject
                tuesday5.text = tuesdayList[4].subject
                tuesday6.text = tuesdayList[5].subject
                tuesday7.text = tuesdayList[6].subject
            } catch (e: java.lang.Exception) {
                toastMsg += "화, "
            }
        }
    }

    private fun fetchWednesday(wednesdayList: List<ScheduleEntity.Schedule>) {
        binding.run {
            try {
                wednesday1.text = wednesdayList[0].subject
                wednesday2.text = wednesdayList[1].subject
                wednesday3.text = wednesdayList[2].subject
                wednesday4.text = wednesdayList[3].subject
                wednesday5.text = wednesdayList[4].subject
                wednesday6.text = wednesdayList[5].subject
                wednesday7.text = wednesdayList[6].subject
            } catch (e: java.lang.Exception) {
                toastMsg += "수, "
            }
        }
    }

    private fun fetchThursday(thursdayList: List<ScheduleEntity.Schedule>) {
        binding.run {
            try {
                thursday1.text = thursdayList[0].subject
                thursday2.text = thursdayList[1].subject
                thursday3.text = thursdayList[2].subject
                thursday4.text = thursdayList[3].subject
                thursday5.text = thursdayList[4].subject
                thursday6.text = thursdayList[5].subject
                thursday7.text = thursdayList[6].subject
            } catch (e: java.lang.Exception) {
                toastMsg += "목, "
            }
        }
    }

    private fun fetchFriday(fridayList: List<ScheduleEntity.Schedule>) {
        binding.run {
            try {
                friday1.text = fridayList[0].subject
                friday2.text = fridayList[1].subject
                friday3.text = fridayList[2].subject
                friday4.text = fridayList[3].subject
                friday5.text = fridayList[4].subject
                friday6.text = fridayList[5].subject
                friday7.text = fridayList[6].subject
            } catch (e: java.lang.Exception) {
                toastMsg += "금"
            }
        }
    }

    override fun observeEvent() {
        mainViewModel.schedule.observe(this) {
            fetchMonday(it.mondayList)
            fetchTuesday(it.tuesdayList)
            fetchWednesday(it.wednesdayList)
            fetchThursday(it.thursdayList)
            fetchFriday(it.fridayList)
            if (toastMsg.isNotEmpty()) {
                showToastShort(toastMsg + "요일에 비는 시간표가 있습니다")
            }
        }
        repeatOnStarted {
            mainViewModel.eventFlow.collect {
                when(it){
                    is MainViewModel.Event.BadRequest -> {
                        showToastShort("학년, 반이 잘 적용되었는지 확인해주세요")
                        mainViewModel.autoSchedule()
                    }
                    is MainViewModel.Event.Forbidden -> {
                        showToastShort("접근 불가 권한입니다")
                        mainViewModel.autoSchedule()
                    }
                    is MainViewModel.Event.NotFound -> {
                        showToastShort("데이터를 찾을 수 없습니다")
                        mainViewModel.autoSchedule()
                    }
                    is MainViewModel.Event.Server -> {
                        showToastShort("서버가 닫혀 있습니다")
                        mainViewModel.autoSchedule()
                    }
                    else -> {
                        showToastShort("알 수 없는 오류")
                        mainViewModel.autoSchedule()
                    }
                }
            }
        }
    }
}