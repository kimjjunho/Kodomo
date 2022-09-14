package com.example.seonsijo

import com.example.seonsijo.base.BaseFragment
import com.example.seonsijo.databinding.FragmentGradeCheckBinding

class GradeCheckFragment : BaseFragment<FragmentGradeCheckBinding>(R.layout.fragment_grade_check){

    override fun initView() {

        gradeNum = 1
        classNum = 1

        binding.run {
            btn1.isChecked = true
        }

        onRadioButtonClick()
    }

    override fun observeEvent() {}

    private fun onRadioButtonClick(){
        binding.radioGroup.setOnCheckedChangeListener { p0, p1 ->
            when (p1) {
                R.id.btn1 -> gradeNum = 1
                R.id.btn2 -> gradeNum = 2
                R.id.btn3 -> gradeNum = 3
            }
        }

    }
}