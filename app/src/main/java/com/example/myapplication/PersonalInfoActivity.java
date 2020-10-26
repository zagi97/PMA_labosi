package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.android.material.textfield.TextInputEditText;

public class PersonalInfoActivity extends AppCompatActivity {

    private String sImeStudenta;
    private TextInputEditText oTietUpisiIme;
    private Button oBtnUpisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        oTietUpisiIme = (TextInputEditText)findViewById(R.id.tietImeStudenta);

        oBtnUpisi = (Button)findViewById(R.id.btnUpisi);
        oBtnUpisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sImeStudenta = oTietUpisiIme.getText().toString();
                Intent oUpisiImeStudentaIntent = new Intent(getApplicationContext(), StudentInfoActivity.class);
                oUpisiImeStudentaIntent.putExtra("imeStudenta", sImeStudenta);
                startActivity(oUpisiImeStudentaIntent);
            }
        });
    }
}