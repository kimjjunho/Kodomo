package com.example.seonsijo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivityTestListBinding

class TestListActivity : BaseActivity<ActivityTestListBinding>
    (R.layout.activity_test_list) {

    override fun initView() {


        binding.run {
            tvTitle.text = intent.getStringExtra("subject")+" 수행평가 일정"

            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    override fun observeEvent() {}
}