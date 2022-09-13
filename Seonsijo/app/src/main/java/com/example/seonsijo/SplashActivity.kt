package com.example.seonsijo

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivitySplashBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash){
    override fun initView() {
        val anim = AnimationUtils.loadAnimation(this,R.anim.splash_yellow_animation)

        var codomo : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

        binding.run {
            GlobalScope.launch {
                imageYellow.startAnimation(anim)
                delay(900)
                codomo.postValue(true)
            }

            codomo.observe(this@SplashActivity){
                if(codomo.value == true){
                    splashButton.visibility = View.VISIBLE
                }
            }

            splashButton.setOnClickListener {}
        }



    }

    override fun observeEvent() {}
}