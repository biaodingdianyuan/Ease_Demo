package com.example.liuhaifeng.ease_demo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.liuhaifeng.ease_demo.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.exceptions.HyphenateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //定义控件
    private LinearLayout contactList_lin, conversationList_lin;
    private ContactListFragment contactListFragment;
    private ConversationListFragment conversationListFragment;
    private Group_List_Activity group_list_fragment;

   boolean k=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 判断sdk是否登录成功过，并没有退出和被踢，否则跳转到登陆界面
        if (!EMClient.getInstance().isLoggedInBefore()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_main);


        init();
    }

    public void init() {
        conversationList_lin = (LinearLayout) findViewById(R.id.conversationList);
        contactList_lin = (LinearLayout) findViewById(R.id.contactList);
        Intent intent=getIntent();
        k=intent.getBooleanExtra("flg",false);
        //会话，通讯录点击
        contactList_lin.setOnClickListener(this);
        conversationList_lin.setOnClickListener(this);
        contactListFragment = new ContactListFragment();
        conversationListFragment = new ConversationListFragment();

        new Thread() {
            @Override
            public void run() {
                //需要设置联系人列表才能启动fragment
                contactListFragment.setContactsMap(getContact());
            }
        }.start();




        //设置联系人item点击事件
        contactListFragment.setContactListItemClickListener(new EaseContactListFragment.EaseContactListItemClickListener() {

            @Override
            public void onListItemClicked(EaseUser user) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername()).putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE + ""));
            }
        });
//
        //设置会话item点击事件
        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

            @Override
            public void onListItemClicked(EMConversation conversation) {
             Intent intent =new Intent(MainActivity.this,ChatActivity.class);
                intent.putExtra(EaseConstant.EXTRA_USER_ID, conversation.getUserName());
                    List<EMGroup> list =EMClient.getInstance().groupManager().getAllGroups();
                for(int i=0;i<list.size();i++){
                    if(conversation.getUserName().equals(list.get(i).getGroupId())){
                        Log.d("huihua",conversation.getUserName());
                        Log.d("qunzu",list.get(i).getGroupId());


                        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE,EaseConstant.CHATTYPE_GROUP+"");
                    }else{

                        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE,EaseConstant.CHATTYPE_SINGLE+"");
                    }

                }
                if (list.size()==0){
                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE,EaseConstant.CHATTYPE_SINGLE+"");
                }
                startActivity(intent);
            }
        });
        if(k) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment, contactListFragment).commit();

        }else {

            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment, conversationListFragment).commit();


        }

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.conversationList:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment, conversationListFragment).commit();
                break;
            case R.id.contactList:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment, contactListFragment).commit();
                break;

        }
    }

    private Map<String, EaseUser> getContact() {
        Map<String, EaseUser> map = new HashMap<>();
        try {
            List<String> userNames = EMClient.getInstance().contactManager().getAllContactsFromServer();

            for (String userId : userNames) {
                Log.d("好友列表中有 : ", userId);
                map.put(userId, new EaseUser(userId));
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return map;
    }
}
