package com.example.liuhaifeng.ease_demo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMGroupManager.EMGroupOptions;
import com.example.liuhaifeng.ease_demo.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;


public class Creat_groupActivity extends AppCompatActivity {
    private Button creat;
    private EditText name,desc,rewason,option_e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_group);
        creat = (Button) findViewById(R.id.creat);
        name= (EditText) findViewById(R.id.group_name_create);
        desc= (EditText) findViewById(R.id.group_desc_create);
        rewason= (EditText) findViewById(R.id.group_reason_create);
        option_e= (EditText) findViewById(R.id.group_option_create);
        /**
         * 创建群组
         * @param groupName 群组名称
         * @param desc 群组简介
         * @param allMembers 群组初始成员，如果只有自己传空数组即可
         * @param reason 邀请成员加入的reason
         * @param option 群组类型选项，可以设置群组最大用户数(默认200)及群组类型@see {@link EMGroupStyle}
         * @return 创建好的group
         * @throws HyphenateException
         */
        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EMGroupOptions option = new EMGroupOptions();
                option.maxUsers = Integer.parseInt(option_e.getText().toString());
                option.style = EMGroupManager.EMGroupStyle.EMGroupStylePrivateMemberCanInvite;
               String [] allMembers={};
                try {
                    EMClient.getInstance().groupManager().createGroup(name.getText().toString(),
                            desc.getText().toString(), allMembers, rewason.getText().toString(), option);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }

            }
        });

    }

}
