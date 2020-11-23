package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;


public class SummaryFragment extends Fragment {

    private String sImeStudenta;
    private String sPrezimeStudenta;
    private String sDatumRođenja;
    private String sNazivPredmeta;
    private String sNazivProfesora;
    private String sAkademskaGodina;
    private String sBrojSatiPredavanja;
    private String sBrojSatiLabosa;

    private TextView oIme;
    private TextView oPrezime;
    private TextView oDatum;
    private TextView oPredmet;
    private TextView oProfesor;
    private TextView oAkGod;
    private TextView oSatiPredavanja;
    private TextView oSatiLva;
    private Button oSumButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_summary, container, false);

        oIme = (TextView)v.findViewById(R.id.tvImeStudenta);
        oPrezime = (TextView)v.findViewById(R.id.tvPrezimeStudenta);
        oDatum = (TextView)v.findViewById(R.id.tvDatumRođenja);
        oPredmet = (TextView)v.findViewById(R.id.tvNazivPredmeta);
        oProfesor = (TextView)v.findViewById(R.id.tvNazivProfesora);
        oAkGod = (TextView)v.findViewById(R.id.tvAkademskaGodina);
        oSatiPredavanja = (TextView)v.findViewById(R.id.tvBrojPredavanja);
        oSatiLva = (TextView)v.findViewById(R.id.tvBrojLabosa);
        oSumButton = (Button)v.findViewById(R.id.btnPovratak);

        oSumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataStorage myDataStorage = MyDataStorage.getInstance();
                Student student = new Student(sImeStudenta, sPrezimeStudenta, sNazivPredmeta);
                myDataStorage.setStudents(student);
                Intent intent = new Intent(getActivity(), PocetniZaslonActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return v;
    }

    public void updatePersonalInfo(String ime, String prezime, String datum){
        sImeStudenta = ime;
        sPrezimeStudenta = prezime;

        oIme.setText(ime);
        oPrezime.setText(prezime);
        oDatum.setText(datum);

    }

    public void updateStudentInfo(String predmet, String ime_profesora, String akademska_godina, String sati_predavanja, String sati_LV) {
        sNazivPredmeta = predmet;

        oPredmet.setText(predmet);
        oProfesor.setText(ime_profesora);
        oAkGod.setText(akademska_godina);
        oSatiPredavanja.setText(sati_predavanja);
        oSatiLva.setText(sati_LV);
    }
}