package com.example.administrator.sweetme.project.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.sweetme.R;
import com.example.administrator.sweetme.base.BaseActivity;
import com.example.administrator.sweetme.bean.UserBean;
import com.example.administrator.sweetme.utils.MD5Utils;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.edt_register)
    EditText edtRegister;
    @Bind(R.id.edt_register_pw_1)
    EditText edtRegisterPw1;
    @Bind(R.id.edt_register_pw_2)
    EditText edtRegisterPw2;
    @Bind(R.id.tv_btn_register)
    TextView tvBtnRegister;
    @Bind(R.id.mkLoader_register)
    MKLoader mkLoaderRegister;

    private String name, passWord, pw1, pw2;
    private UserBean userBean;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        tvBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_btn_register:
                name = edtRegister.getText().toString().trim();
                pw1 = edtRegisterPw1.getText().toString().trim();
                pw2 = edtRegisterPw2.getText().toString().trim();
                if ("".equals(name)) {
                    Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                } else if ("".equals(pw1)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!isFormat(pw1)) {
                    Toast.makeText(this, "请输入6位以上数字或字母", Toast.LENGTH_SHORT).show();
                    edtRegisterPw1.setText("");
                    edtRegisterPw2.setText("");
                    return;
                } else if ("".equals(pw2)) {
                    Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!pw2.equals(pw1)) {
                    Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    edtRegisterPw2.setText("");
                    return;
                } else {
                    //进行加密操作
                    passWord = MD5Utils.encode(pw1, "123");
                    Toast.makeText(this, MD5Utils.decode(passWord, "123"), Toast.LENGTH_SHORT).show();
                    userBean = new UserBean();
                    userBean.setName(name);
                    userBean.setPassWord(passWord);
                    sendData();
                }
                break;
        }
    }

    private void sendData() {
        mkLoaderRegister.setVisibility(View.VISIBLE);
        tvBtnRegister.setVisibility(View.GONE);
        userBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                mkLoaderRegister.setVisibility(View.GONE);
                tvBtnRegister.setVisibility(View.VISIBLE);
                if (null == e) {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });
    }

    private boolean isFormat(String passWord) {
        //正则表达式
        String regEx = "^[a-zA-Z0-9_]\\w{5,17}$";//6-18未
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(passWord);
        boolean b = matcher.matches();
        return b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
