package com.example.omjee.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login, signup, view1;
    EditText userid, password;
    Dbhelper dbhelper;
    String a, b, c, d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper = new Dbhelper(this);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        userid = (EditText) findViewById(R.id.userid);
        password = (EditText) findViewById(R.id.password);
        view1 = (Button) findViewById(R.id.view);
        signup();
        data();
        search();
    }

    public void signup() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(i);
            }
        });
    }

    public void data() {
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbhelper.getdata();
                if (cursor.getCount() == 0) {
                    showmessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("ID:" + cursor.getString(0) + "\n");
                    buffer.append("NAME:" + cursor.getString(1) + "\n");
                    buffer.append("EMAIL:" + cursor.getString(2) + "\n");
                    buffer.append("PHONE:" + cursor.getString(3) + "\n");
                    buffer.append("PASSWORD:" + cursor.getString(4) + "\n");
                }
                showmessage("DATA", buffer.toString());
            }
        });
    }

    public void showmessage(String Title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(message);
        builder.show();
    }

    public void search() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                Cursor res;
                res = dbhelper.searchKeyString(userid.getText().toString(), password.getText().toString());
                if (res.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "YOU are Not registered", Toast.LENGTH_LONG).show();
                }
                if (res.moveToFirst()) {
                    buffer.append("Id :" + res.getString(0) + "\n");
                    buffer.append("Name :" + res.getString(1) + "\n");
                    buffer.append("Email :" + res.getString(2) + "\n");
                    buffer.append("Phone :" + res.getString(3) + "\n");
                    buffer.append("Paswword :" + res.getString(4) + "\n\n");
                    Toast.makeText(MainActivity.this,buffer,Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),Main3Activity.class);
                    startActivity(intent);
                }
            }
        });
    }
}


