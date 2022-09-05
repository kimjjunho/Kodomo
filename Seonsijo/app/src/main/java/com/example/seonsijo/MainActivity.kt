package com.example.seonsijo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivityMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initView() {
        binding.run {

            btnGradeNum.setOnClickListener {
                slidingPanelLayout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                Log.d("TAG", "initView: ")
            }

            btnClassNum.setOnClickListener {
            }

            btnBeforeWeek.setOnClickListener {
            }

            btnAfterWeek.setOnClickListener {

            }
        }
    }

    override fun observeEvent() {
    }
}