package com.example.seonsijo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.seonsijo.base.BaseActivity
import com.example.seonsijo.databinding.ActivitySignUpBinding
import com.example.seonsijo.databinding.FragmentGradeCheckBinding
import com.example.seonsijo.main.MainActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up){

    val gradeNum : MutableLiveData<Int> = MutableLiveData<Int>()
    val classNum : MutableLiveData<Int> = MutableLiveData<Int>()

    override fun initView() {

        gradeNum.value = 1
        classNum.value = 1

        supportFragmentManager.beginTransaction().replace(R.id.frame,GradeCheckFragment()).commit()

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
                        .replace(R.id.frame,ClassCheckFragment())
                        .addToBackStack("grade")
                        .commit()

                    tvTitle.text = "반 입력"
                }
                else{
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.putExtra("gradeNum",tvGradeNum.text.toString())
                    intent.putExtra("classNum",tvClassNum.text.toString())
                    startActivity(intent)
                    finish()
                }
            }
        }

    }

    override fun observeEvent() {}

}