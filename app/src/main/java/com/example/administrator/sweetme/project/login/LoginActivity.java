package com.example.administrator.sweetme.project.login;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.sweetme.MainActivity;
import com.example.administrator.sweetme.R;
import com.example.administrator.sweetme.bean.User;
import com.example.administrator.sweetme.bean.UserBean;
import com.example.administrator.sweetme.utils.MD5Utils;
import com.example.administrator.sweetme.utils.SPUtils;
import com.example.administrator.sweetme.utils.ScaleUtils;
import com.socks.library.KLog;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import rx.Observer;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.iv_login)
    ImageView ivLogin;
    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.login_clear)
    ImageView loginClear;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.login)
    TextView login;
    @Bind(R.id.checkbox)
    CheckBox checkbox;
    @Bind(R.id.textlogin)
    TextView textlogin;
    @Bind(R.id.user_register)
    TextView userRegister;
    @Bind(R.id.tv_btn_register)
    TextView tvBtnRegister;
    private String name,pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);
        userRegister.setOnClickListener(this);
        tvBtnRegister.setOnClickListener(this);
        login.setOnClickListener(this);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_register:
                Intent intent1 = new Intent(this, RegisterActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_btn_register:
                ScaleUtils.getScaleAnimator(tvBtnRegister);
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
                break;
            case R.id.login:
                 name = username.getText().toString().trim();
                 pw = password.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(pw)){
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    login();
                }

                break;
        }
    }

    private void login() {
        BmobQuery<UserBean> query = new BmobQuery<>();
        query.addWhereEqualTo("name",name);
        query.findObjects(new FindListener<UserBean>() {
            @Override
            public void done(List<UserBean> list, BmobException e) {
                KLog.e(e);
                KLog.e(list);
                if (null==e&&null!=list&&list.size()>0){
                    for (int i=0;i<list.size();i++){
                        String passWord = MD5Utils.decode(list.get(i).getPassWord(),"123");
                        if (passWord.equals(pw)){
                        SPUtils spUtils =    SPUtils.getInstance(getApplicationContext());
                            User user = new User();
                            user.setName(name);
                            user.setPassWord(passWord);
                            spUtils.putObject("user",user);
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent  = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "密码校验错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "没有该用户", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
