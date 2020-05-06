package com.rxy.aop.aoptest.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/*
 * Author: rxy
 * Create: 2020/5/6 9:09 AM
 * Email:rxywxsy@gmail.com
 * Changes (from 2020/5/6)
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("peter", javaClass.simpleName)
        setContentView(loadLayout())
        setUp()
    }

    abstract fun loadLayout(): Int

    abstract fun setUp()
}