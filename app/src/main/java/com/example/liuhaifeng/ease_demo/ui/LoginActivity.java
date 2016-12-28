package com.example.liuhaifeng.ease_demo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.liuhaifeng.ease_demo.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{
private EditText email,password;
    private Button sign_in;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        sign_in= (Button) findViewById(R.id.sign_in_button);
        sign_in.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                EMClient.getInstance().login(email.getText().toString(), password.getText().toString(), new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        startActivity(new Intent(LoginActivity.this,MainActivity.class).putExtra("flg",false));
                    }

                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });



    }


}

