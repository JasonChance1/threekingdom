package com.example.threekingdom.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class BaseBindingActivity<VB : ViewBinding>(
    val block: (LayoutInflater) -> VB
) : BaseActivity() {
    private var _binding: VB? = null
    private var isDestroyed = false
    private val binding: VB
        get() = requireNotNull(_binding) { "The property of binding has been destroyed." }
    /**
     * 沙盒缓存目录
     */
    private lateinit var cachePath:String
    override fun onCreate(savedInstanceState: Bundle?) {

        isDestroyed = false
        cachePath = externalCacheDir?.path.toString()
        beforeOnCrate()
        super.onCreate(savedInstanceState)
        _binding = block(layoutInflater)
        setContentView(binding.root)
        if(!isDestroyed){
            initData()
            initView()
        }
    }

    open fun initData() {}
    open fun beforeOnCrate() {}

    open fun addObserver() {}
    open fun initView() {}
    open fun bindEvent() {}
    open fun removeObserver() {}

    override fun onStart() {
        addObserver()
        bindEvent()
        super.onStart()
    }

    override fun onStop() {
        removeObserver()
        super.onStop()
    }

    /**
     * 子类的所有binding相关的操作都需要放到super.onDestroy前进行
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        isDestroyed = true
    }
}