package com.example.seonsijo.signup

import com.example.seonsijo.R
import com.example.seonsijo.base.BaseFragment
import com.example.seonsijo.databinding.FragmentGradeCheckBinding
import com.example.seonsijo.main.MainActivity

class GradeCheckFragment : BaseFragment<FragmentGradeCheckBinding>(R.layout.fragment_grade_check){

    override fun initView() {

        MainActivity.gradeNum = 1

        binding.run {
            btn1.isChecked = true
        }

        onRadioButtonClick()
    }

    override fun observeEvent() {}

    private fun onRadioButtonClick(){
        binding.radioGroup.setOnCheckedChangeListener { p0, p1 ->
            when (p1) {
                R.id.btn1 -> MainActivity.gradeNum = 1
                R.id.btn2 -> MainActivity.gradeNum = 2
                R.id.btn3 -> MainActivity.gradeNum = 3
            }
        }

    }
}