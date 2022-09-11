package com.example.seonsijo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivityAlarmBinding

class AlarmActivity : BaseActivity<ActivityAlarmBinding>(R.layout.activity_alarm){
    override fun initView() {
        binding.run {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    override fun observeEvent() {}
}