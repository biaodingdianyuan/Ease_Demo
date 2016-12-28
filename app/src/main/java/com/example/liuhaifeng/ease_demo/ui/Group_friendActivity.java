package com.example.liuhaifeng.ease_demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.liuhaifeng.ease_demo.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by liuhaifeng on 2016/12/10.
 */

public class Group_friendActivity extends AppCompatActivity {
    private EditText name;
    String groupId;
    private Button add;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_friend_activity);
        Intent intent = getIntent();
        groupId = intent.getStringExtra("groupId");
        name = (EditText) findViewById(R.id.friend_name_group);

        add = (Button) findViewById(R.id.add_friend_group);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.d("-----------",groupId);
                                String[] newname = {name.getText().toString()};

                                Log.d("-----------",name.getText().toString());
                                //EMClient.getInstance().groupManager().setAutoAcceptInvitation(true);
                                EMClient.getInstance().groupManager().addUsersToGroup(groupId,newname);//
                                Log.d("-----------",newname.length+"");
                            } catch (HyphenateException e) {
                                e.printStackTrace();
                                Log.d("-----------",e+"");
                            }
                        }
                    }).start();


            }
        });
    }

}
