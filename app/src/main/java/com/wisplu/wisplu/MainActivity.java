package com.wisplu.wisplu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kii.cloud.storage.Kii;
import com.kii.cloud.storage.KiiUser;
import com.kii.cloud.storage.callback.KiiUserCallBack;


public class MainActivity extends AppCompatActivity {

    private Button LOGIN;
    private Button REGISTER;
    private TextView id;
    private EditText Ed_username;
    private EditText Ed_password;
    private String email;
    private String password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Kii.onRestoreInstanceState(savedInstanceState);
        setContentView(R.layout.activity_main);

        initKiiSDK();

        LOGIN = (Button) findViewById(R.id.LOGIN);
        REGISTER = (Button) findViewById(R.id.REGISTER);
        id = (TextView) findViewById(R.id.id);
        Ed_username = (EditText) findViewById(R.id.Ed_username);
        Ed_password = (EditText) findViewById(R.id.Ed_password);
        email = Ed_username.getText().toString();
        password = Ed_password.getText().toString();

        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Ed_username.getText().toString();
                password = Ed_password.getText().toString();
                System.out.println("username" + email + "password" + password);
                System.out.println("check" + !KiiUser.isValidUserName(email));
                // check
                if (!KiiUser.isValidUserName(email)) {

                    System.out.println("無效帳號 return");
                    return;
                }
                if (!KiiUser.isValidPassword(password)) {
                    System.out.println("無效密碼 return");
                    return;
                }


                // call user login API
                KiiUser.logIn(new KiiUserCallBack() {
                    @Override
                    public void onLoginCompleted(int token, KiiUser user, Exception e) {
                        super.onLoginCompleted(token, user, e);
                        if (e != null) {
                            System.out.println("Register completed error" + e);
                            id.setText("登陸 失敗");
                            return;
                        }
                        id.setText("登陸成功");
                    }


                }, email, password);


            }

        });

        REGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Ed_username.getText().toString();
                password = Ed_password.getText().toString();

                // call user registration API
                KiiUser user = KiiUser.builderWithName(email).build();
                user.register(new KiiUserCallBack() {
                    @Override
                    public void onRegisterCompleted(int token, KiiUser user, Exception e) {
                        super.onRegisterCompleted(token, user, e);

                        if (e != null) {
                            System.out.println("Register completed error" + e);
                            id.setText("Register 失敗");
                            return;
                        }
                        id.setText("Register 成功");
                    }
                }, password);
            }
        });


    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Kii SDK states
        Kii.onSaveInstanceState(outState);
    }

    private void initKiiSDK() {
        System.out.println("kii init");
        Kii.initialize(
                Constants.APP_ID,  // Put your App ID
                Constants.APP_KEY, // Put your App Key
                Kii.Site.CN3           // Put your site as you've specified upon creating the app on the dev portal
        );
    }

}
