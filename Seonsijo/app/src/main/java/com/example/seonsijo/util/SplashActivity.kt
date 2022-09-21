package com.example.seonsijo.util

import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.MutableLiveData
import com.example.seonsijo.R
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivitySplashBinding
import com.example.seonsijo.main.MainActivity
import com.example.seonsijo.signup.SignUpActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash){

    override fun initView() {

        val anim = AnimationUtils.loadAnimation(this, R.anim.splash_yellow_animation)

        var codomo : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

        classNum = MyApplication.prefs.getString("classNum","1").toInt()
        gradeNum = MyApplication.prefs.getString("gradeNum","1").toInt()
        gradeClassCheck = MyApplication.prefs.getBoolean("check",false)

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

            splashButton.setOnClickListener {
                if(gradeClassCheck){
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                }else{
                    startActivity(Intent(applicationContext, SignUpActivity::class.java))
                }
            }
        }



    }

    override fun observeEvent() {}
}