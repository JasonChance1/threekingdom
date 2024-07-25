package com.example.threekingdom.base

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @description:
 * @author M&G
 * @date :2023/9/5
 * @version 1.0.0
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev!!.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (isHideInput(view, ev)) {
                hideSoftInput(view?.windowToken)
                view?.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }


    private fun isHideInput(v: View?, ev: MotionEvent): Boolean {
        if (v != null && (v is EditText)) {
            val l = arrayListOf(0, 0).toIntArray()
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.getHeight()
            val right = left + v.getWidth()
            return !(ev.x > left && ev.x < right && ev.y > top && ev.y < bottom)
        }
        return false
    }

    private fun hideSoftInput(token: IBinder?) {
        if (token != null) {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    fun intentTo(clazz: Class<*>, needFinish: Boolean = false) {
        val intent = Intent(this, clazz)
        startActivity(intent)
        if (needFinish) finish()
    }

    fun runOnUi(exceptionCallback: ((Exception) -> Unit)? = null, f: suspend () -> Unit) {
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                f.invoke()
            } catch (e: Exception) {
                e.printStackTrace()
                exceptionCallback?.invoke(e)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
    }
}