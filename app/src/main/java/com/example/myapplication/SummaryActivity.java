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

    private String sNazivPredmeta;
    private TextView oNazivPredmeta;

    private Button oBtnPovratak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        final Bundle oExtras = getIntent().getExtras();

        sImeStudenta = oExtras.getString("imeStudenta");
        oImeStudenta = (TextView)findViewById(R.id.tvImeStudenta);
        oImeStudenta.setText(sImeStudenta);

        sNazivPredmeta = oExtras.getString("nazivPredmeta");
        oNazivPredmeta = (TextView)findViewById(R.id.tvNazivPredmeta);
        oNazivPredmeta.setText(sNazivPredmeta);

        oBtnPovratak = (Button)findViewById(R.id.btnPovratak);
        oBtnPovratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oPovratakNaPrvuStranicu = new Intent(getApplicationContext(), PersonalInfoActivity.class);
                oPovratakNaPrvuStranicu.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(oPovratakNaPrvuStranicu);

            }
        });
    }
}