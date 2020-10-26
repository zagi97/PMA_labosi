package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class StudentInfoActivity extends AppCompatActivity {

    private String sNazivPredmeta;
    private TextInputEditText oTietUpisiPredmet;

    private String sImeStudenta;

    private Button oBtnUpisiPredmet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        final Bundle oExtras = getIntent().getExtras();

        sImeStudenta = oExtras.getString("imeStudenta");

        oTietUpisiPredmet = (TextInputEditText)findViewById(R.id.tietNazivPredmeta);

        oBtnUpisiPredmet = (Button)findViewById(R.id.btnUpisPredmeta);
        oBtnUpisiPredmet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sNazivPredmeta = oTietUpisiPredmet.getText().toString();

                Intent oUpisiNazivPredmetaIntent = new Intent(getApplicationContext(), SummaryActivity.class);
                oUpisiNazivPredmetaIntent.putExtra("nazivPredmeta",sNazivPredmeta);
                oUpisiNazivPredmetaIntent.putExtra("imeStudenta", sImeStudenta);
                startActivity(oUpisiNazivPredmetaIntent);
            }
        });
    }
}