package com.example.threekingdom

import android.app.Application
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @description:
 * @author M&G
 * @date :2023/8/31
 * @version 1.0.0
 */
class MainApplication : Application() {


    companion object {
        lateinit var instance: MainApplication
        fun instance() = instance
    }


    override fun onCreate() {
        super.onCreate()
        instance = this


        CoroutineScope(Dispatchers.IO).launch {
            Glide.get(applicationContext)
        }

    }

}