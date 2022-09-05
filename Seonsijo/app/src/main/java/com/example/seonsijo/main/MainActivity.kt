package com.example.seonsijo.main

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seonsijo.R
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivityMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initView() {

        useRecyclerView()

        binding.run {
            btnBeforeWeek.setOnClickListener {

            }

            btnAfterWeek.setOnClickListener {

            }
        }
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