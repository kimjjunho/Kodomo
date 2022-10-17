package com.example.seonsijo.signup

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.domain.entity.signup.SignUpEntity
import com.example.seonsijo.R
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivitySignUpBinding
import com.example.seonsijo.main.MainActivity
import com.example.seonsijo.util.device_token
import com.example.seonsijo.util.repeatOnStarted
import com.google.firebase.FirebaseApp

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up){

    private val signUpViewModel: SignUpViewModel by viewModels()

    private val gradeNum : MutableLiveData<Int> = MutableLiveData<Int>()
    private val classNum : MutableLiveData<Int> = MutableLiveData<Int>()

    override fun initView() {
        FirebaseApp.initializeApp(this)


        gradeNum.value = 1
        classNum.value = 1

        supportFragmentManager.beginTransaction().replace(R.id.frame, GradeCheckFragment()).commit()

        binding.run {

            tvGradeNum.text = classNum.value.toString()
            tvClassNum.text = classNum.value.toString()

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
                            grade = tvGradeNum.text.toString().toInt(),
                            class_num = tvClassNum.text.toString().toInt(),
                            device_token = device_token
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
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            intent.putExtra("gradeNum",tvGradeNum.text.toString())
                            intent.putExtra("classNum",tvClassNum.text.toString())
                            startActivity(intent)
                            finish()
                        }
                    }
                    is SignUpViewModel.Event.BadRequest -> {
                        showToastShort("디바이스 토큰을 불러올 수 없습니다")
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