package com.vcooline.li.videoplayer.view.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.category.ProfileActivity;

/**
 * Created by Trace on 2014/8/21.
 */
public class LoginAndRegisterDialog {
    public  Activity activity;
    public  LayoutInflater inflater;
                        View layout;
    public LoginAndRegisterDialog(Activity activity){
        this.activity = activity;
        inflater = activity.getLayoutInflater();
    }
    public  AlertDialog getLoginDialog(){
       // LayoutInflater inflater = activity.getLayoutInflater();
        layout = inflater.inflate(R.layout.dialog_login, null);
        final EditText username = (EditText)layout.findViewById(R.id.username);
        final EditText password = (EditText)layout.findViewById(R.id.password);
        TextView zc_view = (TextView)layout.findViewById(R.id.zc_v);
        TextView forget_pa = (TextView)layout.findViewById(R.id.forgetPs_v);//忘记密码的操作
        Button login = (Button)layout.findViewById(R.id.login_btn);
        zc_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                //Toast.makeText(activity,"TextView is dian ji l",Toast.LENGTH_SHORT).show();
                getRegisterDialog();
            }
        });
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String usernameTxt = username.getText().toString().trim();
                String passwordTxt = password.getText().toString().trim();
                if(usernameTxt.equals("")){
                    Toast.makeText(activity,"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(passwordTxt.equals("")){
                    Toast.makeText(activity,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                ((ProfileActivity)activity).login(usernameTxt,passwordTxt);
            }
        });
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle("登陆").
                setView(layout)
                .create();
        return dialog;
    }
      public void getRegisterDialog(){
        layout = inflater.inflate(R.layout.dialog_register,null);
        final EditText nickname = (EditText)layout.findViewById(R.id.nickname);
        final EditText username = (EditText)layout.findViewById(R.id.username);
        final EditText password = (EditText)layout.findViewById(R.id.password);
        Button login = (Button)layout.findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String nicknameText = nickname.getText().toString().trim();
                String usernameTxt = username.getText().toString().trim();
                String passwordTxt = password.getText().toString().trim();
                if(usernameTxt.equals("")){
                    Toast.makeText(activity,"用户昵称不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(usernameTxt.equals("")){
                    Toast.makeText(activity,"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(passwordTxt.equals("")){
                    Toast.makeText(activity,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle("登陆").
                        setView(layout)
                .create();
                dialog.show();
    }

}
