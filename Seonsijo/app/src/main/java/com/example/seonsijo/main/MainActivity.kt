package com.example.seonsijo.main

import android.annotation.SuppressLint
import androidx.activity.viewModels
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
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
import org.openjdk.tools.javac.Main
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.Year
import java.util.Calendar
import java.util.GregorianCalendar
import javax.inject.Inject
import javax.security.auth.Subject
import kotlin.math.abs
import kotlin.math.min

@AndroidEntryPoint
class MainActivity @Inject constructor() : BaseActivity<ActivityMainBinding>
    (R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()

    private var startAt = ""
    private var endAt = ""
    private var startYear = 0
    private var startMonth = 0
    private var startDay = 0
    private var endYear = 0
    private var endMonth = 0
    private var endDay = 0

    private val calendar = GregorianCalendar()

    @SuppressLint("SimpleDateFormat")
    private val date: String = SimpleDateFormat("yyyyMMdd").format(Date(System.currentTimeMillis()))

    @SuppressLint("SetTextI18n")
    override fun initView() {
        classTextChange()

        useRecyclerView()

        mainViewModel.localSchedule()

        // tableClickEvent()
        var startCalendarCheckDay = 0
        var endCalendarCheckDay = 0

        when (calendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> {
                startCalendarCheckDay = 1
                endCalendarCheckDay = 5
            }
            2 -> {
                startCalendarCheckDay = 0
                endCalendarCheckDay = 4
            }
            3 -> {
                startCalendarCheckDay = -1
                endCalendarCheckDay = 3
            }
            4 -> {
                startCalendarCheckDay = -2
                endCalendarCheckDay = 2
            }
            5 -> {
                startCalendarCheckDay = -3
                endCalendarCheckDay = 1
            }
            6 -> {
                startCalendarCheckDay = -4
                endCalendarCheckDay = 0
            }
            7 -> {
                startCalendarCheckDay = 2
                endCalendarCheckDay = 6
            }
        }

        val startCalendar = GregorianCalendar(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE) + startCalendarCheckDay
        )

        val endCalendar = GregorianCalendar(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE) + endCalendarCheckDay
        )

        startYear = startCalendar.get(Calendar.YEAR)
        startMonth = startCalendar.get(Calendar.MONTH)
        startDay = startCalendar.get(Calendar.DATE)

        endYear = endCalendar.get(Calendar.YEAR)
        endMonth = endCalendar.get(Calendar.MONTH)
        endDay = endCalendar.get(Calendar.DATE)

        endAt = endMapper()
        startAt = startMapper()

        setTitleText()

        binding.run {

            tvGrade.text = gradeNum.toString() + "학년"

            tvClass.text = classNum.toString() + "반"

            btnBeforeWeek.setOnClickListener {
                checkDay--

                setTitleText()

                startAt = startMapper()
                endAt = endMapper()

                mainViewModel.getSchedule(
                    ScheduleParam(
                        grade = gradeNum,
                        class_num = classNum,
                        startAt = startAt,
                        endAt = endAt,
                    )
                )

                if (checkDay <= -2) {
                    btnBeforeWeek.visibility = View.INVISIBLE
                }

                if (checkDay < 2) {
                    btnAfterWeek.visibility = View.VISIBLE
                }
            }

            btnAfterWeek.setOnClickListener {
                checkDay++

                setTitleText()

                startAt = startMapper()
                endAt = endMapper()

                mainViewModel.getSchedule(
                    ScheduleParam(
                        grade = gradeNum,
                        class_num = classNum,
                        startAt = startAt,
                        endAt = endAt,
                    )
                )

                if (checkDay >= 2) {
                    btnAfterWeek.visibility = View.INVISIBLE
                }
                if (checkDay > -2) {
                    btnBeforeWeek.visibility = View.VISIBLE
                }

            }

            btnAlarm.setOnClickListener {
                startActivity(Intent(this@MainActivity, AlarmActivity::class.java))
            }
            gradeTextChange()
        }
    }

    companion object SignUpVariable {
        var gradeNum = 1
        var classNum = 1
        var device_token: String? = null
        var checkDay = 0
    }

    private fun dateMapper(year: Int, month: Int, day: Int): String =
        year.toString() + "-" + String.format("%02d", month) + "-" + String.format("%02d", day)

    private fun startMapper(): String {
        val calendar = GregorianCalendar(startYear, startMonth, startDay + 7 * checkDay)

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DATE)

        return dateMapper(year, month, day)
    }


    private fun endMapper(): String {
        val calendar = GregorianCalendar(endYear, endMonth, endDay + 7 * checkDay)

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DATE)

        return dateMapper(year, month, day)
    }

    private fun tableClickEvent() {
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

    private fun classTextChange() {
        binding.tvClass.addTextChangedListener(object : TextWatcher {
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

    private fun gradeTextChange() {
        binding.tvGrade.addTextChangedListener(object : TextWatcher {
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
            if (!this.text.isNullOrEmpty()) {
                gotoTestListActivity(this)
            }
        }
    }

    private fun gotoTestListActivity(textView: TextView) {
        val intent = Intent(applicationContext, TestListActivity::class.java)
        intent.putExtra("subject", textView.text.toString())

        startActivity(intent)
    }

    private fun useRecyclerView() {
        binding.run {
            btnGradeNum.setOnClickListener {
                slidingPanelLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

                slidingPanelRecyclerview.apply {
                    adapter = MainAdapter(listOf("1학년", "2학년", "3학년"), this@MainActivity, true)
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    setHasFixedSize(true)
                }
            }

            btnClassNum.setOnClickListener {
                slidingPanelLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

                slidingPanelRecyclerview.apply {
                    adapter = MainAdapter(listOf("1반", "2반", "3반", "4반"), this@MainActivity, false)
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    setHasFixedSize(true)
                }
            }
        }
    }

    private fun setTitleText() {
        binding.run {
            if (checkDay == 0) {
                tvScheduleWeek.text = "이번주 시간표"
            } else {
                if (checkDay < 0) {
                    tvScheduleWeek.text = abs(checkDay).toString() + "주 전 시간표"
                } else {
                    tvScheduleWeek.text = checkDay.toString() + "주 후 시간표"
                }
            }
        }
    }

    private fun fetchMonday(mondayList: List<ScheduleEntity.Schedule>) {
        binding.run {
            try {
                monday1.text = subjectPatch(mondayList[0].name)
                monday2.text = subjectPatch(mondayList[1].name)
                monday3.text = subjectPatch(mondayList[2].name)
                monday4.text = subjectPatch(mondayList[3].name)
                monday5.text = subjectPatch(mondayList[4].name)
                monday6.text = subjectPatch(mondayList[5].name)
                monday7.text = subjectPatch(mondayList[6].name)
            } catch (e: java.lang.Exception) { }
        }
    }

    private fun fetchTuesday(tuesdayList: List<ScheduleEntity.Schedule>) {
        binding.run {

            try {
                tuesday1.text = subjectPatch(tuesdayList[0].name)
                tuesday2.text = subjectPatch(tuesdayList[1].name)
                tuesday3.text = subjectPatch(tuesdayList[2].name)
                tuesday4.text = subjectPatch(tuesdayList[3].name)
                tuesday5.text = subjectPatch(tuesdayList[4].name)
                tuesday6.text = subjectPatch(tuesdayList[5].name)
                tuesday7.text = subjectPatch(tuesdayList[6].name)
            } catch (e: java.lang.Exception) { }
        }
    }

    private fun fetchWednesday(wednesdayList: List<ScheduleEntity.Schedule>) {
        binding.run {
            try {
                wednesday1.text = subjectPatch(wednesdayList[0].name)
                wednesday2.text = subjectPatch(wednesdayList[1].name)
                wednesday3.text = subjectPatch(wednesdayList[2].name)
                wednesday4.text = subjectPatch(wednesdayList[3].name)
                wednesday5.text = subjectPatch(wednesdayList[4].name)
                wednesday6.text = subjectPatch(wednesdayList[5].name)
                wednesday7.text = subjectPatch(wednesdayList[6].name)
            } catch (e: java.lang.Exception) { }
        }
    }

    private fun fetchThursday(thursdayList: List<ScheduleEntity.Schedule>) {
        binding.run {
            try {
                thursday1.text = subjectPatch(thursdayList[0].name)
                thursday2.text = subjectPatch(thursdayList[1].name)
                thursday3.text = subjectPatch(thursdayList[2].name)
                thursday4.text = subjectPatch(thursdayList[3].name)
                thursday5.text = subjectPatch(thursdayList[4].name)
                thursday6.text = subjectPatch(thursdayList[5].name)
                thursday7.text = subjectPatch(thursdayList[6].name)
            } catch (e: java.lang.Exception) { }
        }
    }

    private fun fetchFriday(fridayList: List<ScheduleEntity.Schedule>) {
        binding.run {
            try {
                friday1.text = subjectPatch(fridayList[0].name)
                friday2.text = subjectPatch(fridayList[1].name)
                friday3.text = subjectPatch(fridayList[2].name)
                friday4.text = subjectPatch(fridayList[3].name)
                friday5.text = subjectPatch(fridayList[4].name)
                friday6.text = subjectPatch(fridayList[5].name)
                friday7.text = subjectPatch(fridayList[6].name)
            } catch (e: java.lang.Exception) { }
        }
    }

    private fun subjectPatch(subject: String): String {
        return if (subject.length > 5) {
            when (subject) {
                "컴퓨터 네트워크" -> "컴네"
                "성공적인 직업생활" -> "성직"
                "프로젝트실무Ⅰ", "프로젝트실무Ⅱ" -> "프실"
                "* 빅테이터 분석 결과 시각" -> "빅분"
                "확률과 통계" -> "확통"
                "C++ 프로그래밍" -> "C++"
                "자료구조와 알고리즘" -> "자료구조"
                "알고리즘실무" -> "알고리즘\n실무"
                "웹프로그래밍" -> "웹프"
                "소프트웨어공학 실무" -> "소공"
                "임베디드 리눅스 프로그래밍" -> "리눅스"
                "마이크로프로세서응용" -> "마프"
                else -> subject
            }
        } else subject
    }

    override fun observeEvent() {
        repeatOnStarted {
            mainViewModel.schedule.collect {
                cleanText()
                signUpViewModel.updateSignUpVariable(
                    SignUpEntity(
                        grade = gradeNum,
                        class_num = classNum,
                        device_token = device_token,
                        check_day = checkDay
                    )
                )
                fetchMonday(it.mondayList)
                fetchTuesday(it.tuesdayList)
                fetchWednesday(it.wednesdayList)
                fetchThursday(it.thursdayList)
                fetchFriday(it.fridayList)
            }
        }
        repeatOnStarted {
            mainViewModel.eventFlow.collect {
                cleanText()
                when (it) {
                    is MainViewModel.Event.BadRequest -> showToastShort("학년, 반이 잘 적용되었는지 확인해주세요")
                    is MainViewModel.Event.Forbidden -> showToastShort("접근 불가 권한입니다")
                    is MainViewModel.Event.NotFound -> showToastShort("데이터를 찾을 수 없습니다")
                    is MainViewModel.Event.ManyRequest -> showToastShort("요청이 너무 많습니다")
                    is MainViewModel.Event.Server -> showToastShort("서버가 닫혀있습니다")
                    is MainViewModel.Event.NoInternet, MainViewModel.Event.TimeOut -> {
                        showToastShort("인터넷 연결 상태를 확인해주세요")
                        mainViewModel.localSchedule()
                    }
                }
            }
        }
    }

    private fun cleanText() {
        binding.run {
            monday1.text = ""
            monday2.text = ""
            monday3.text = ""
            monday4.text = ""
            monday5.text = ""
            monday6.text = ""
            monday7.text = ""
            tuesday1.text = ""
            tuesday2.text = ""
            tuesday3.text = ""
            tuesday4.text = ""
            tuesday5.text = ""
            tuesday6.text = ""
            tuesday7.text = ""
            wednesday1.text = ""
            wednesday2.text = ""
            wednesday3.text = ""
            wednesday4.text = ""
            wednesday5.text = ""
            wednesday6.text = ""
            wednesday7.text = ""
            thursday1.text = ""
            thursday2.text = ""
            thursday3.text = ""
            thursday4.text = ""
            thursday5.text = ""
            thursday6.text = ""
            thursday7.text = ""
            friday1.text = ""
            friday2.text = ""
            friday3.text = ""
            friday4.text = ""
            friday5.text = ""
            friday6.text = ""
            friday7.text = ""
        }
    }
}

