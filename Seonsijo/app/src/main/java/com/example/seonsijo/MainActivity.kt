package com.example.seonsijo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initView() {
        binding.run {
            btnGradeNum.setOnClickListener {

            }

            btnGradeNum.setOnClickListener {

            }
        }
    }

    override fun observeEvent() {
    }
}