package com.example.bhanuka.nicreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nic;
    Button decode;
    TextView de_nic, de_gender, de_birth, de_age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Variables
        try {
            decode = (Button) findViewById(R.id.btn_decode);
            nic = (EditText) findViewById(R.id.text_nic);
            de_nic = (TextView) findViewById(R.id.de_nic);
            de_gender = (TextView) findViewById(R.id.de_gender);
            de_birth = (TextView) findViewById(R.id.de_birth);
            de_age = (TextView) findViewById(R.id.de_age);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        decode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    NIC modelClass = new NIC(nic.getText().toString());
                    de_nic.setText(modelClass.getNicNumber());
                    de_gender.setText(modelClass.getGender());
                    de_birth.setText(modelClass.getBirthDay());
                    de_age.setText(modelClass.getAge());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });


    }
}
