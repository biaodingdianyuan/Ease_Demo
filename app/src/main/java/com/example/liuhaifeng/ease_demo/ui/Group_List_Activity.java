package com.example.liuhaifeng.ease_demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liuhaifeng.ease_demo.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.widget.EaseTitleBar;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Group_List_Activity extends AppCompatActivity {
    private ListView lv;
    private List<EMGroup> list;
    private GroupAdapter adapter;
    private EaseTitleBar titleBar;
    private Button create;
    private ContactListFragment listFragment;


    public Group_List_Activity() {

    }

    Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
          switch (msg.what){
              case 0:
                  adapter.notifyDataSetChanged();
          }



        }
    };





    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_group__list_);
        lv = (ListView) findViewById(R.id.groupList);
        listFragment=new ContactListFragment();
        create= (Button) findViewById(R.id.create_group_btn);
        titleBar= (EaseTitleBar) findViewById(R.id.group_title_bar);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(Group_List_Activity.this,MainActivity.class).putExtra("flg",true));

                finish();
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Group_List_Activity.this,Add_friendActivity.class).putExtra("exe","addtogroup"));
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Group_List_Activity.this,Creat_groupActivity.class));

            }
        });



        list = new ArrayList<EMGroup>();
        list = EMClient.getInstance().groupManager().getAllGroups();
        adapter = new GroupAdapter(list);
        lv.setAdapter(adapter);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    list = EMClient.getInstance().groupManager().getJoinedGroupsFromServer();

                    handler.sendEmptyMessage(0);


                } catch (HyphenateException e) {
                    e.printStackTrace();
                }

            }
        }).start();


//        //lv的点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(Group_List_Activity.this,ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, list.get(i).getGroupId()).putExtra(EaseConstant.EXTRA_CHAT_TYPE,EaseConstant.CHATTYPE_GROUP+""));


            }
        });
    }



    class GroupAdapter extends BaseAdapter {
        private List<EMGroup> list;

        public GroupAdapter(List<EMGroup> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.group_item, null);
            TextView group_name = (TextView) view.findViewById(R.id.group_name);
            group_name.setText(list.get(i).getGroupName());

            return view;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
finish();
    }
}
