package com.example.seonsijo.util

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.seonsijo.R
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivitySplashBinding
import com.example.seonsijo.main.MainActivity
import com.example.seonsijo.signup.SignUpActivity
import com.example.seonsijo.signup.SignUpViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash){

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun initView() {
        signUpViewModel.signIn()

        val anim = AnimationUtils.loadAnimation(this, R.anim.splash_yellow_animation)
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

            splashButton.setOnClickListener {
                getDeviceToken()

                Log.d("TAG", "initView: "+ MainActivity.gradeNum)
                Log.d("TAG", "initView: "+ MainActivity.classNum)

                if(MainActivity.gradeNum != 0 && MainActivity.classNum != 0 && !MainActivity.device_token.isNullOrEmpty()){
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                }else{
                    startActivity(Intent(applicationContext, SignUpActivity::class.java))
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
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d("TAG", "getDeviceToken: $token")
            Log.d("TAG", "getDeviceToken: $msg")
            MainActivity.device_token = token
        })
    }


    override fun observeEvent() {
        repeatOnStarted {
            signUpViewModel.signIn.collect{
                MainActivity.gradeNum = it.grade
                MainActivity.classNum = it.class_num
                MainActivity.device_token = it.device_token
            }
        }
    }
}