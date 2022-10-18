package com.example.seonsijo.signup

import com.example.seonsijo.R
import com.example.seonsijo.base.BaseFragment
import com.example.seonsijo.databinding.FragmentClassCheckBinding
import com.example.seonsijo.main.MainActivity


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
                R.id.btn1 -> MainActivity.classNum = 1
                R.id.btn2 -> MainActivity.classNum = 2
                R.id.btn3 -> MainActivity.classNum = 3
                R.id.btn4 -> MainActivity.classNum = 4
            }
        }
    }
}