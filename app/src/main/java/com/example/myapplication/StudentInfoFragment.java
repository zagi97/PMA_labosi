package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

import static android.app.Activity.RESULT_OK;

public class StudentInfoFragment extends Fragment {

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

    private StudentInfoFragment.StudentInfoListener studentInfoListener;

    public interface StudentInfoListener
    {
        void onStudentInfoSent(String nazivPredmeta, String nazivProfesora, String akGodina, String satiPredavanja, String satiLabosa);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_student_info, container, false);

        oUpisiPredmet = (TextInputEditText)v.findViewById(R.id.tietNazivPredmeta);
        oUpisiProfesora = (TextInputEditText)v.findViewById(R.id.tietNazivProfesora);
        oUpisiAkademskuGodinu = (TextInputEditText)v.findViewById(R.id.tietUpisAkademskeGodine);
        oUpisiBrojSatiPredavanja = (TextInputEditText)v.findViewById(R.id.tietUpisSatiPredavanja);
        oUpisiBrojSatiLabosa = (TextInputEditText)v.findViewById(R.id.tietUpisSatiLabosa);
        oBtnUpisiPredmet = (Button)v.findViewById(R.id.btnUpisPredmeta);

        oUpisiPredmet.addTextChangedListener(StudentInfoWatcher);
        oUpisiProfesora.addTextChangedListener(StudentInfoWatcher);
        oUpisiAkademskuGodinu.addTextChangedListener(StudentInfoWatcher);
        oUpisiBrojSatiPredavanja.addTextChangedListener(StudentInfoWatcher);
        oUpisiBrojSatiLabosa.addTextChangedListener(StudentInfoWatcher);

        oBtnUpisiPredmet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewRecordActivity.setCurrentItem (2, true);
            }
        });

        return v;
    }

    private TextWatcher StudentInfoWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            sNazivPredmeta = oUpisiPredmet.getText().toString();
            sNazivProfesora = oUpisiProfesora.getText().toString();
            sAkademskaGodina = oUpisiAkademskuGodinu.getText().toString();
            sBrojSatiPredavanja = oUpisiBrojSatiPredavanja.getText().toString();
            sBrojSatiLabosa = oUpisiBrojSatiLabosa.getText().toString();

            studentInfoListener.onStudentInfoSent(sNazivPredmeta, sNazivProfesora, sAkademskaGodina, sBrojSatiPredavanja, sBrojSatiLabosa);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof StudentInfoFragment.StudentInfoListener) {
            studentInfoListener = (StudentInfoFragment.StudentInfoListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        studentInfoListener = null;
    }

}