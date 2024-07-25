package com.example.threekindom.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @description:
 * @author Wandervogel
 * @date :2024/3/21
 * @version 1.0.0
 */
abstract class BaseBindingFragment<VB : ViewBinding>(val initBinding: (LayoutInflater) -> VB) :
    Fragment() {
    private var _binding: VB? = null
    private var isDestroy = false

    /**
     * 沙盒缓存目录
     */
    lateinit var cachePath:String
    protected val binding: VB
        get() = requireNotNull(_binding) { initBinding(layoutInflater) }

    // 加载动画父布局
    private var container:ViewGroup? = null
    private var loadingView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        cachePath = requireContext().externalCacheDir?.path.toString()
        beforeCreate()
        isDestroy = false
        _binding = initBinding(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isDestroy && isAdded) {
            initData()
            initView()
            bindEvent()
        }
    }


    open fun beforeCreate() {}

    /**
     * 初始化数据，onViewCreated后第一个调用的方法
     */
    open fun initData() {}

    /**
     * 初始化视图，initData后执行
     */
    open fun initView() {}

    /**
     * 事件绑定，initView后执行
     */
    open fun bindEvent() {}

    /**
     * 添加观察者，onStart中执行
     */
    open fun addObserver() {}

    /**
     * 移除观察者,onStop中执行
     */
    open fun removeObserver() {}


    override fun onStart() {
        addObserver()
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        removeObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        isDestroy = true
    }

    @SuppressLint("WrongConstant")
    fun hideSoftInput() {
        val im = requireContext().getSystemService("input_method") as InputMethodManager
        im.hideSoftInputFromWindow(binding.root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

}