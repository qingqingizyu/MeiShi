package com.example.administrator.android_a1607_okhttp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.administrator.android_a1607_okhttp.DataBase.NewsDao;
import com.example.administrator.android_a1607_okhttp.DataBaseTwo.NewsDaoTwo;
import com.example.administrator.android_a1607_okhttp.R;

/**
 * 登录界面
 */
public class RegisterActivity extends AppCompatActivity {


    private String userName_re;
    private String passWord_re;
    private TextInputEditText user;
    private TextInputEditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        user = (TextInputEditText) findViewById(R.id.et_user_re);
        pass = (TextInputEditText) findViewById(R.id.ed_passWord_re);
    }

    public void DengLu(View view) {
        userName_re = user.getText().toString().trim();
        passWord_re = pass.getText().toString().trim();
        if (!TextUtils.isEmpty(userName_re) && !TextUtils.isEmpty(passWord_re) && userName_re.length() == 11 && (passWord_re.length() >= 6 && passWord_re.length() <= 16)) {
            NewsDaoTwo dao = new NewsDaoTwo(this);
            String sql = "select * from users";
            Cursor cursor = dao.select(sql, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String username = cursor.getString(cursor.getColumnIndex("username"));
                    String password = cursor.getString(cursor.getColumnIndex("password"));
                    if (username.equals(userName_re) && password.equals(passWord_re)) {
                        Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                }
                finish();
            } else {
                Toast.makeText(this, "登录失败！", Toast.LENGTH_SHORT).show();
            }
        } else {
            user.setError("错误，请检查登录名，登录名为11位手机号");
            pass.setError("错误，请检查密码，密码不能为中文");
            Toast.makeText(this, "登录失败！,请检查密码和账号是否符合要求，要求账号为手机号码，密码为6-16位字符串", Toast.LENGTH_SHORT).show();
        }
    }
}
