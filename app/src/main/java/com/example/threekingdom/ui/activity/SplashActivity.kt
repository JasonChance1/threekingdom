package com.example.threekingdom.ui.activity

import android.annotation.SuppressLint
import com.example.threekindom.databinding.ActivitySplashBinding
import com.example.threekingdom.base.BaseBindingActivity
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseBindingActivity<ActivitySplashBinding>({ActivitySplashBinding.inflate(it)}) {
    override fun initView() {
        runOnUi {
            delay(2000)
            intentTo(MainActivity::class.java,true)
        }
    }
}