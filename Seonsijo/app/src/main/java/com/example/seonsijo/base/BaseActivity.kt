package com.example.seonsijo.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B: ViewDataBinding>(
    @LayoutRes private val layoutId: Int
): AppCompatActivity() {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,layoutId)
        binding.lifecycleOwner = this

        initView()

        observeEvent()
    }

    fun showToastShort(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun showToastLong(text: String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    abstract fun initView()

    abstract fun observeEvent()
}