package com.example.seonsijo.test

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seonsijo.R
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivityTestListBinding

class TestListActivity : BaseActivity<ActivityTestListBinding>
    (R.layout.activity_test_list) {

    private val testList = arrayListOf<TestListData>().apply {
        add(TestListData(1,"제목","이름"))
    }

    override fun initView() {

        binding.run {
            tvTitle.text = intent.getStringExtra("subject")+" 수행평가 일정"

            rvTestList.apply {
                adapter = TestListAdapter(testList,this@TestListActivity)
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
            }

            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    override fun observeEvent() {}

    fun gotoTestActivity(title: String){
        val intent = Intent(applicationContext, TestActivity::class.java)
        intent.putExtra("title",title)

        startActivity(intent)
    }
}