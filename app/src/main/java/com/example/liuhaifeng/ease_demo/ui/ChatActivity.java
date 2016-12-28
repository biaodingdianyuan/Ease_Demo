package com.example.liuhaifeng.ease_demo.ui;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.example.liuhaifeng.ease_demo.R;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;


public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        String name = intent.getStringExtra(EaseConstant.EXTRA_USER_ID);
        String type=intent.getStringExtra(EaseConstant.EXTRA_CHAT_TYPE+"");
        //调用easeUI中的EaseChatFrabment
        chatFragment chatFragment = new chatFragment();
        Bundle args = new Bundle();
        //声明聊天类型 Chat:单聊, GroupChat:群聊, ChatRoom:聊天室
        if(type.equals(EaseConstant.CHATTYPE_SINGLE+"")){
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);}
        else if(type.equals(EaseConstant.CHATTYPE_GROUP+"")){
            args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);}

        //传入聊天对象的ID
        args.putString(EaseConstant.EXTRA_USER_ID, name);
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();


    }

}
