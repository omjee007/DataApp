package com.example.omjee.project;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText id, name, email, phone, pswd;
    Button updation;
    Dbhelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        dbhelper = new Dbhelper(this);
        id = (EditText) findViewById(R.id.id);
        name = (EditText) findViewById(R.id.named);
        email = (EditText) findViewById(R.id.emailed);
        phone = (EditText) findViewById(R.id.phn);
        pswd = (EditText) findViewById(R.id.pswed);
        updation = (Button) findViewById(R.id.update);
        updateadata();
    }

    public void updateadata() {
        updation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isupdate = dbhelper.updatedata(id.getText().toString(), name.getText().toString(), email.getText().toString(), phone.getText().toString(), pswd.getText().toString());
                if (isupdate == true) {
                    Toast.makeText(Main3Activity.this, "DATA IS UPDATED", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Main3Activity.this, "DATA NOT UPDATED", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}


