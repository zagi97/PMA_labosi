package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class StudentInfoActivity extends AppCompatActivity {

    private String sImeStudenta;
    private String sPrezimeStudenta;
    private String sDatumRođenja;

    private String sNazivPredmeta;
    private String sNazivProfesora;
    private String sAkademskaGodina;
    private String sBrojSatiPredavanja;
    private String sBrojSatiLabosa;
    private TextInputEditText oUpisiPredmet;
    private TextInputEditText oUpisiProfesora;
    private TextInputEditText oUpisiAkademskuGodinu;
    private TextInputEditText oUpisiBrojSatiPredavanja;
    private TextInputEditText oUpisiBrojSatiLabosa;


    private Button oBtnUpisiPredmet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        final Bundle oExtras = getIntent().getExtras();

        sImeStudenta = oExtras.getString("imeStudenta");
        sPrezimeStudenta = oExtras.getString("prezimeStudenta");
        sDatumRođenja = oExtras.getString("datumRođenja");

        oUpisiPredmet = (TextInputEditText)findViewById(R.id.tietNazivPredmeta);
        oUpisiProfesora = (TextInputEditText)findViewById(R.id.tietNazivProfesora);
        oUpisiAkademskuGodinu = (TextInputEditText)findViewById(R.id.tietUpisAkademskeGodine);
        oUpisiBrojSatiPredavanja = (TextInputEditText)findViewById(R.id.tietUpisSatiPredavanja);
        oUpisiBrojSatiLabosa = (TextInputEditText)findViewById(R.id.tietUpisSatiLabosa);

        oBtnUpisiPredmet = (Button)findViewById(R.id.btnUpisPredmeta);
        oBtnUpisiPredmet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sNazivPredmeta = oUpisiPredmet.getText().toString();
                sNazivProfesora = oUpisiProfesora.getText().toString();
                sAkademskaGodina = oUpisiAkademskuGodinu.getText().toString();
                sBrojSatiPredavanja = oUpisiBrojSatiPredavanja.getText().toString();
                sBrojSatiLabosa = oUpisiBrojSatiLabosa.getText().toString();

                Intent oUpisiNazivPredmetaIntent = new Intent(getApplicationContext(), SummaryActivity.class);
                oUpisiNazivPredmetaIntent.putExtra("imeStudenta", sImeStudenta);
                oUpisiNazivPredmetaIntent.putExtra("prezimeStudenta", sPrezimeStudenta);
                oUpisiNazivPredmetaIntent.putExtra("datumRođenja", sDatumRođenja);
                oUpisiNazivPredmetaIntent.putExtra("nazivPredmeta",sNazivPredmeta);
                oUpisiNazivPredmetaIntent.putExtra("nazivProfesora", sNazivProfesora);
                oUpisiNazivPredmetaIntent.putExtra("akademskaGodina", sAkademskaGodina);
                oUpisiNazivPredmetaIntent.putExtra("brojPredavanja", sBrojSatiPredavanja);
                oUpisiNazivPredmetaIntent.putExtra("brojLabosa", sBrojSatiLabosa);
                startActivity(oUpisiNazivPredmetaIntent);
            }
        });
    }
}