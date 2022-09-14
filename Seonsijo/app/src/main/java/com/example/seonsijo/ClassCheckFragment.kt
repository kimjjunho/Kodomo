package com.example.seonsijo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seonsijo.base.BaseFragment
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