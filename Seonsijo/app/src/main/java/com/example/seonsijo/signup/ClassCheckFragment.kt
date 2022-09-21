package com.example.seonsijo.signup

import com.example.seonsijo.R
import com.example.seonsijo.base.BaseFragment
import com.example.seonsijo.util.classNum
import com.example.seonsijo.databinding.FragmentClassCheckBinding


class ClassCheckFragment : BaseFragment<FragmentClassCheckBinding>(R.layout.fragment_class_check){

    override fun initView() {

        binding.run {
            btn1.isChecked = true
        }

        onRadioButton()
    }

    override fun observeEvent() {}

    private fun onRadioButton(){
        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.btn1 -> classNum = 1
                R.id.btn2 -> classNum = 2
                R.id.btn3 -> classNum = 3
                R.id.btn4 -> classNum = 4
            }
        }
    }
}