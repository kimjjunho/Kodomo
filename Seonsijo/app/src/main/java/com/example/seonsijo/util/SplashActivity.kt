package com.example.seonsijo.util

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.seonsijo.R
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivitySplashBinding
import com.example.seonsijo.main.MainActivity
import com.example.seonsijo.signup.SignUpViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.openjdk.tools.javac.Main

@SuppressLint("CustomSplashScreen")
@OptIn(DelicateCoroutinesApi::class)
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash){

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun initView() {
        signUpViewModel.signIn()

        val anim = AnimationUtils.loadAnimation(this, R.anim.splash_yellow_animation)
        val codomo : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

        binding.run {
            GlobalScope.launch {
                imageYellow.startAnimation(anim)
                delay(900)
                codomo.postValue(true)
            }

            codomo.observe(this@SplashActivity){
                if(codomo.value == true){
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
            }
        }
    }

    private fun getDeviceToken() {
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if(!task.isSuccessful){
                return@OnCompleteListener
            }
            val token = task.result
            MainActivity.device_token = token
        })
    }

    override fun observeEvent() {
        repeatOnStarted {
            signUpViewModel.signIn.collect{
                MainActivity.gradeNum = it.grade
                MainActivity.classNum = it.class_num
                MainActivity.checkDay = it.checkDay
            }
        }
    }
}