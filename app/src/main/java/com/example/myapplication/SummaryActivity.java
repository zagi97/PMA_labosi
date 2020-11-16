package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private String sImeStudenta;
    private TextView oImeStudenta;

    private String sPrezimeStudenta;
    private TextView oPrezimeStudenta;

    private String sDatumRođenja;
    private TextView oDatumRođenja;

    private String sNazivProfesora;
    private TextView oNazivProfesora;

    private String sNazivPredmeta;
    private TextView oNazivPredmeta;

    private String sAkademskaGodina;
    private TextView oAkademskaGodina;

    private String sBrojPredavanja;
    private TextView oBrojPredavanja;

    private String sBrojLabosa;
    private TextView oBrojLabosa;

    private Button oBtnPovratak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        final Bundle oExtras = getIntent().getExtras();

        sImeStudenta = oExtras.getString("imeStudenta");
        oImeStudenta = (TextView)findViewById(R.id.tvImeStudenta);
        oImeStudenta.setText(sImeStudenta);

        sPrezimeStudenta = oExtras.getString("prezimeStudenta");
        oPrezimeStudenta = (TextView)findViewById(R.id.tvPrezimeStudenta);
        oPrezimeStudenta.setText(sPrezimeStudenta);

        sDatumRođenja = oExtras.getString("datumRođenja");
        oDatumRođenja = (TextView)findViewById(R.id.tvDatumRođenja);
        oDatumRođenja.setText(sDatumRođenja);

        sNazivProfesora = oExtras.getString("nazivProfesora");
        oNazivProfesora = (TextView)findViewById(R.id.tvNazivProfesora);
        oNazivProfesora.setText(sNazivProfesora);

        sNazivPredmeta = oExtras.getString("nazivPredmeta");
        oNazivPredmeta = (TextView)findViewById(R.id.tvNazivPredmeta);
        oNazivPredmeta.setText(sNazivPredmeta);

        sAkademskaGodina = oExtras.getString("akademskaGodina");
        oAkademskaGodina = (TextView)findViewById(R.id.tvAkademskaGodina);
        oAkademskaGodina.setText(sAkademskaGodina);

        sBrojPredavanja = oExtras.getString("brojPredavanja");
        oBrojPredavanja = (TextView)findViewById(R.id.tvBrojPredavanja);
        oBrojPredavanja.setText(sBrojPredavanja);

        sBrojLabosa = oExtras.getString("brojLabosa");
        oBrojLabosa = (TextView)findViewById(R.id.tvBrojLabosa);
        oBrojLabosa.setText(sBrojLabosa);

        oBtnPovratak = (Button)findViewById(R.id.btnPovratak);
        oBtnPovratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oPovratakNaPrvuStranicu = new Intent(getApplicationContext(), PocetniZaslonActivity.class);
                oPovratakNaPrvuStranicu.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(oPovratakNaPrvuStranicu);

            }
        });
    }
}