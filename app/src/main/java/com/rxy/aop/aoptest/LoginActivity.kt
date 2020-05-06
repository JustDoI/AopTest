package com.rxy.aop.aoptest

import android.app.Activity
import android.content.Context
import com.rxy.aop.aoptest.base.BaseActivity
import com.rxy.aop.aoptest.config.Constant
import kotlinx.android.synthetic.main.activity_login.*

/*
 * Author: rxy
 * Create: 2020/5/6 9:08 AM
 * Email:rxywxsy@gmail.com
 * Changes (from 2020/5/6)
 */
class LoginActivity : BaseActivity() {

    override fun loadLayout() = R.layout.activity_login

    override fun setUp() {
        bt_login.setOnClickListener {
            val sp = getSharedPreferences("default", Context.MODE_PRIVATE)
            val editor=sp.edit()
            editor.putBoolean(Constant.KEY_IS_LOGIN,true).apply()
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

}