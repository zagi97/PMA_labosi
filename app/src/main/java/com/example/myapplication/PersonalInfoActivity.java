package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.material.textfield.TextInputEditText;

public class PersonalInfoActivity extends AppCompatActivity {

    private String sImeStudenta;
    private String sPrezimeStudenta;
    private String sDatum;
    private TextInputEditText oUpisiIme;
    private TextInputEditText oUpisiPrezime;
    private EditText oUpisDatuma;
    private Button oBtnUpisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        oUpisiIme = (TextInputEditText)findViewById(R.id.tietImeStudenta);
        oUpisiPrezime = (TextInputEditText)findViewById(R.id.tietPrezimenaStudenta);
        oUpisDatuma = (EditText)findViewById(R.id.etUpisDatuma);

        oBtnUpisi = (Button)findViewById(R.id.btnUpisi);
        oBtnUpisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sImeStudenta = oUpisiIme.getText().toString();
                sPrezimeStudenta = oUpisiPrezime.getText().toString();
                sDatum = oUpisDatuma.getText().toString();

                Intent oUpisiImeStudentaIntent = new Intent(getApplicationContext(), StudentInfoActivity.class);
                oUpisiImeStudentaIntent.putExtra("imeStudenta", sImeStudenta);
                oUpisiImeStudentaIntent.putExtra("prezimeStudenta", sPrezimeStudenta);
                oUpisiImeStudentaIntent.putExtra("datumRođenja", sDatum);
                startActivity(oUpisiImeStudentaIntent);
            }
        });
    }
}