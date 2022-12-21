package com.example.seonsijo.signup

import android.content.Intent
import androidx.activity.viewModels
import com.example.domain.entity.signup.SignUpEntity
import com.example.seonsijo.R
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivitySignUpBinding
import com.example.seonsijo.main.MainActivity
import com.example.seonsijo.util.repeatOnStarted
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import org.openjdk.tools.javac.Main

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up){

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun initView() {
        FirebaseApp.initializeApp(this)

        supportFragmentManager.beginTransaction().replace(R.id.frame, GradeCheckFragment()).commit()

        binding.run {

            btnBack.setOnClickListener {
                tvTitle.text = "학년 입력"
                onBackPressed()
            }

            btnNext.setOnClickListener {
                if(supportFragmentManager.backStackEntryCount == 0){
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ClassCheckFragment())
                        .addToBackStack("grade")
                        .commit()

                    tvTitle.text = "반 입력"
                }
                else{
                    signUpViewModel.signUp(
                        SignUpEntity(
                            grade = MainActivity.gradeNum.toString().toInt(),
                            class_num = MainActivity.classNum.toString().toInt(),
                            device_token = MainActivity.device_token,
                            check_day = MainActivity.checkDay
                        )
                    )
                }
            }
        }

    }

    override fun observeEvent() {
        repeatOnStarted {
            signUpViewModel.eventFlow.collect{
                when(it){
                    is SignUpViewModel.Event.Success -> {
                        binding.run {
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                        }
                    }
                    is SignUpViewModel.Event.BadRequest -> {
                        showToastShort("디바이스 토큰을 불러올 수 없습니다. 앱을 다시 실행해주세요")
                    }
                    is SignUpViewModel.Event.NotFound -> {
                        showToastShort("학년, 반을 찾을 수 없습니다")
                    }
                    is SignUpViewModel.Event.Server -> {
                        showToastShort("서버가 닫혀있습니다")
                    }
                }
            }
        }
    }

}