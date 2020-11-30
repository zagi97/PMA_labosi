package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PocetniZaslonActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener
        {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button oBtnKreirajStudenta;

    String[] languages = { "Cro", "Eng", "Hun"};
    String[] items_value = new String[]{"hr", "en", "hu"};
    int check = 0;

    private void setAppLocale (String localeCode)
    {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(localeCode.toLowerCase()));
        }
        else {
            conf.locale = new Locale(localeCode.toLowerCase());
        }
        res.updateConfiguration(conf, dm);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni_zaslon);

        mRecyclerView = (RecyclerView)findViewById(R.id.rvListaStudenata);

        oBtnKreirajStudenta = (Button)findViewById(R.id.btnNoviStudent);
        oBtnKreirajStudenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oOtvoriPersonalInfo = new Intent(getApplicationContext(), CreateNewRecordActivity.class);
                startActivity(oOtvoriPersonalInfo);
            }
        });


        MyDataStorage spremnik = MyDataStorage.getInstance();
        List<Object> students = spremnik.getStudents();

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerAdapter(students);
        mRecyclerView.setAdapter(mAdapter);

        Spinner spin = (Spinner) findViewById(R.id.spinJezici);
        Locale current = getResources().getConfiguration().locale;


        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,languages);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        if(current.getLanguage() == "hr")
        {
            spin.setSelection(0);
        }
        if(current.getLanguage() == "en")
        {
            spin.setSelection(1);
        }
        if(current.getLanguage() == "hu")
        {
            spin.setSelection(2);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        String pickedLang = items_value[position];
        String languageToLoad  = pickedLang;

        //TextView test = (TextView)findViewById(R.id.test);
        //test.setText(languageToLoad);

        setAppLocale(languageToLoad);
        if(++check > 1){
            finish();
            startActivity(getIntent());
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}