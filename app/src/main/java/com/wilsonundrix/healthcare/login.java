package com.wilsonundrix.healthcare;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class login extends AppCompatActivity {
    EditText usr,psw;
    Button login,fgtpsw,signup;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDb = new DatabaseHelper(this);
        usr = (EditText)findViewById(R.id.etUsr);
        psw = (EditText)findViewById(R.id.etPsw);
        login = (Button)findViewById(R.id.btnSignIn);
        fgtpsw = (Button)findViewById(R.id.btnFgtPsw);
        signup = (Button)findViewById(R.id.btnSignUp);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usr.getText().toString();
                String pass = psw.getText().toString();

                Cursor res = myDb.getUser(user,pass);
                if(res.getCount() == 0){
                    Toast.makeText(getApplicationContext(),user + " log in FAILED : ",Toast.LENGTH_SHORT).show();
                }else if(res.getCount() == 1){
                    Toast.makeText(getApplicationContext(),user + " log in SUCCESS ",Toast.LENGTH_SHORT).show();
                    if (user.equals("admin")){
                        Intent intent = new Intent("com.wilsonundrix.healthcare.Dashboard_Doctor");
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent("com.wilsonundrix.healthcare.Dashboard_Student");
                        startActivity(intent);
                    }
                }

            }
        });
        fgtpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),R.string.press_fgt_psw_btn,Toast.LENGTH_SHORT).show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),R.string.press_sign_up_btn,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("com.wilsonundrix.healthcare.Sign_Up");
                startActivity(intent);
            }
        });

    }
}
