package com.example.seonsijo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivityTestBinding

class TestActivity : BaseActivity<ActivityTestBinding>(R.layout.activity_test) {
    override fun initView() {
       binding.run {
           btnBack.setOnClickListener {
               finish()
           }

           tvTitle.text = intent.getStringExtra("title")
       }
    }

    override fun observeEvent() {}

}