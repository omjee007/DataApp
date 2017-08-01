package com.example.omjee.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText username, email, phone, password, conpassword;
    Button create_account, back_login;
    Dbhelper dbhelper;
    String string = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,}$";
    String phonein="^[789]\\d{9}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dbhelper = new Dbhelper(this);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password1);
        conpassword = (EditText) findViewById(R.id.conpassword);
        create_account = (Button) findViewById(R.id.create_account);
        back_login = (Button) findViewById(R.id.back_login);
        creation();
        intent();
    }

    public void intent() {
        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void creation() {

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().length() != 0 && password.getText().toString().length() != 0 && email.getText().toString().length() != 0 && password.getText().toString().length() != 0) {
                    if (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                        if (password.getText().toString().equals(conpassword.getText().toString())) {
                            if (password.getText().toString().matches(string)) {
                                if(phone.getText().toString().matches(phonein)){
                                    Boolean isinserted = dbhelper.insertdata(username.getText().toString(), email.getText().toString(), phone.getText().toString(), password.getText().toString());
                                    if (isinserted == true) {
                                        Toast.makeText(getApplicationContext(), "ACCOUNT CREATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "ACCOUNT NOT CREATED", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {Toast.makeText(getApplicationContext(),"please enter the phone no.in correct format",Toast.LENGTH_SHORT).show();}

                            } else {
                                Toast.makeText(getApplicationContext(), "Password must be 6 characters long and contain one special character and one uppercase or lower case", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Please enter the same password as above", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter the email.correct format", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
