package com.example.administrator.sweetme.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by FancyMenG on 2017/10/10.
 */

public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        initView();
    }
    protected abstract int setLayoutId();

    protected abstract void initView();

    //不带数据的跳转
    public<T extends Activity> void startActivity( Class<T> c){
        startActivity(new Intent(BaseActivity.this,c));
    }
    //带有数据的跳转
    public <T extends Activity>void startDataActivity(Intent intent,Class<T> c){
        startActivity(intent);
    }


    public void exit(){

    }
}
