package com.example.dini_v02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.txt_user_login);
        mTextPassword = (EditText)findViewById(R.id.txt_pass_login);
        mButtonLogin = (Button)findViewById(R.id.btn_login);
        mTextViewRegister = (TextView)findViewById(R.id.register_now);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean red = db.checkUser(user, pwd);
                if (red == true)
                {
                    Toast.makeText(LoginActivity.this, "Welcome back g!", Toast.LENGTH_SHORT).show();
                   //Intent doomTime = new Intent(LoginActivity.this,DoomActivity.class);
                    //startActivity(doomTime);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Something is wrong, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}