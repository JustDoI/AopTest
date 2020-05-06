package com.rxy.aop.aoptest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.rxy.aop.aoptest.annotation.CheckLogin;
import com.rxy.aop.aoptest.annotation.TimeCount;
import com.rxy.aop.aoptest.base.BaseActivity;
import com.rxy.aop.aoptest.config.Constant;

/*
 * Author: rxy
 * Create: 2020/5/6 9:06 AM
 * Email:rxywxsy@gmail.com
 * Changes (from 2020/5/6)
 */
public class MainActivity extends BaseActivity {

    private AppCompatButton mLoginButton;
    private AppCompatButton mCountButton;

    @Override
    public int loadLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setUp() {
        SharedPreferences sp = getSharedPreferences("default", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(Constant.KEY_IS_LOGIN, false).apply();
        mLoginButton = findViewById(R.id.bt_next);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSecond();
            }
        });

        mCountButton = findViewById(R.id.bt_count);
        mCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count();
            }
        });
    }

    @CheckLogin(loginCode = 1011)
    public void toSecond() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @TimeCount
    public void count() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 1000; i++) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.SECOND_LOGIN) {
            toSecond();
        }
    }
}
