package com.example.dini_v02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper( this);
        mTextUsername = (EditText)findViewById(R.id.txt_user_regist);
        mTextPassword = (EditText)findViewById(R.id.txt_pass_regist);
        mTextCnfPassword = (EditText)findViewById(R.id.txt_pass_cnf);
        mButtonRegister = (Button)findViewById(R.id.btn_register);
        mTextViewLogin = (TextView)findViewById(R.id.login_now);
        mTextViewLogin.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();

                if(pwd.equals(pwd))
                {
                    long val = db.addUser(user, cnf_pwd);
                    if(val > 0 )
                    {
                        Toast.makeText(RegisterActivity.this,"Registration successful!", Toast.LENGTH_SHORT).show();
                        Intent moveToLoginPage = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(moveToLoginPage);
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this,"Registration error!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Password not much, try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}