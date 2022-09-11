package com.example.seonsijo.main

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seonsijo.AlarmActivity
import com.example.seonsijo.R
import com.example.seonsijo.testlist.TestListActivity
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivityMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity : BaseActivity<ActivityMainBinding>
    (R.layout.activity_main) {

    override fun initView() {

        classTextChange()
        gradeTextChange()

        useRecyclerView()

        tableClickEvent()

        binding.run {
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

            gotoT(btnTuesday1, tuesday1)
            gotoT(btnTuesday2, tuesday2)
            gotoT(btnTuesday3, tuesday3)
            gotoT(btnTuesday4, tuesday4)
            gotoT(btnTuesday5, tuesday5)

            gotoT(btnWednesday1, wednesday1)
            gotoT(btnWednesday2, wednesday2)
            gotoT(btnWednesday3, wednesday3)
            gotoT(btnWednesday4, wednesday4)
            gotoT(btnWednesday5, wednesday5)

            gotoT(btnThursday1, thursday1)
            gotoT(btnThursday2, thursday2)
            gotoT(btnThursday3, thursday3)
            gotoT(btnThursday4, thursday4)
            gotoT(btnThursday5, thursday5)

            gotoT(btnFriday1, friday1)
            gotoT(btnFriday2, friday2)
            gotoT(btnFriday3, friday3)
            gotoT(btnFriday4, friday4)
            gotoT(btnFriday5, friday5)

        }
    }

    private fun classTextChange(){
        binding.tvClass.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun gradeTextChange(){
        binding.tvGrade.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

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
    }
}