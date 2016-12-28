package com.example.liuhaifeng.ease_demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.liuhaifeng.ease_demo.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.exceptions.HyphenateException;

public class Add_friendActivity extends AppCompatActivity {
    private EditText friend_name;
    private Button add_friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        final Intent intent=getIntent();


        friend_name= (EditText) findViewById(R.id.friend_name);
        add_friend= (Button) findViewById(R.id.add_friend);



        add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(intent.getStringExtra("exe").equals("addfriend")){
                    try {
                        EMClient.getInstance().contactManager().addContact(friend_name.getText().toString(),"请求加为好友");
                    } catch (HyphenateException e) {
                        e.printStackTrace();

                    }
                }else{



                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    EMClient.getInstance().groupManager().applyJoinToGroup(friend_name.getText().toString(),"1111");
                                } catch (HyphenateException e) {
                                    e.printStackTrace();
                                    Log.d("2121111111111",e.toString());
                                }

                            }
                        }).start();


                           // EMClient.getInstance().groupManager().joinGroup(friend_name.getText().toString());



                }



            }
        });
    }

}
