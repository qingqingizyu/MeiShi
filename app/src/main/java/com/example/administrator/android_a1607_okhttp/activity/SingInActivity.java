package com.example.administrator.android_a1607_okhttp.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.android_a1607_okhttp.Bean.UserBean;
import com.example.administrator.android_a1607_okhttp.DataBaseTwo.NewsDaoTwo;
import com.example.administrator.android_a1607_okhttp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.Bmob;

import static android.os.Build.ID;

/**
 * 注册界面
 */

public class SingInActivity extends AppCompatActivity {

    private TextInputEditText et_user;
    private TextInputEditText et_passWord;
    private EditText yanNum;
    private String name;
    private String passWord;
    private String yan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        initView();//初始化
        BmobSMS.initialize(this, "35f5ad5f4bdd4d96d8494f18ef75f91e");
    }


    //初始化
    private void initView() {

        et_user = (TextInputEditText) findViewById(R.id.et_user);
        et_passWord = (TextInputEditText) findViewById(R.id.ed_passWord);
        yanNum = (EditText) findViewById(R.id.et_yanNum);

    }

    //判断
    //注册按钮
    public void Register(View view) {
        name = et_user.getText().toString();
        passWord = et_passWord.getText().toString();
        yan = yanNum.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(passWord) || TextUtils.isEmpty(yan)) {
            et_passWord.setError("用户名和密码,验证码不能为空!");
            et_user.setError("用户名和密码,验证码不能为空!");
            Toast.makeText(this, "用户名和密码,验证码不能为空!", Toast.LENGTH_SHORT).show();
        } else if (name.length() == 11 && (passWord.length() >= 6 && passWord.length() <= 16)) {
            et_passWord.setError("正确");
            et_user.setError("正确");

            BmobSMS.verifySmsCode(this, name, yan, new VerifySMSCodeListener() {

                NewsDaoTwo dao = new NewsDaoTwo(SingInActivity.this);

                @Override
                public void done(BmobException ex) {
                    // TODO Auto-generated method stub
                    if (ex == null) {//短信验证码已验证成功
                        Log.i("bmob", "验证通过");
                        Toast.makeText(SingInActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                        ContentValues values = new ContentValues();
                        values.put("username", name);
                        values.put("password", passWord);
                        long insert = dao.insert("users", values);
                        if (insert > 0) {
                            Toast.makeText(SingInActivity.this, "注册成功!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SingInActivity.this,MainActivity.class));
                            finish();
                        }

                    } else {
                        Log.i("bmob", "验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                        Toast.makeText(SingInActivity.this, "验证失败，请重新验证", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //获取验证码
    public void Yanzhengma(View view) {
        name = et_user.getText().toString();
        passWord = et_passWord.getText().toString();
        yan = yanNum.getText().toString();
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(passWord) && TextUtils.isEmpty(yan)) {
            Toast.makeText(this, "用户名和密码,验证码不能为空!", Toast.LENGTH_SHORT).show();
        } else {
            BmobSMS.requestSMSCode(this, name, "美视", new RequestSMSCodeListener() {

                @Override
                public void done(Integer smsId, BmobException ex) {
                    // TODO Auto-generated method stub
                    if (ex == null) {//验证码发送成功
                        Log.i("bmob", "短信id：" + smsId);//用于查询本次短信发送详情
                        Toast.makeText(SingInActivity.this, "短信发送成功", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(SingInActivity.this, "短信发送失败，请检查手机号码!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
