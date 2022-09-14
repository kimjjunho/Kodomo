package com.example.seonsijo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment <B: ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
): Fragment(){
    val binding get() = mBinding!!
    private var mBinding: B ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        initView()
        observeEvent()
        return binding.root

    }

    abstract fun initView()

    abstract fun observeEvent()

    fun showToast(massage: String){
        Toast.makeText(context, massage, Toast.LENGTH_SHORT).show()
    }
}