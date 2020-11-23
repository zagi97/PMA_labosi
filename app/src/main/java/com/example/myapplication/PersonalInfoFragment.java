package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import static android.app.Activity.RESULT_OK;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PersonalInfoFragment extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1 ;

    private ImageView imageView;
    private String sImeStudenta;
    private String sPrezimeStudenta;
    private String sDatum;

    private TextInputEditText oIme;
    private TextInputEditText oPrezime;
    private EditText oDatuma;
    private Button oBtnUpisi;

    private PersonalInfoListener personalInfoListener;

    public interface PersonalInfoListener
    {
        void onPersonalInfoSent(String ime, String prezime, String datum);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_info,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.ivSlikaKorisnika);
        oIme = (TextInputEditText)view.findViewById(R.id.tietImeStudenta);
        oPrezime = (TextInputEditText)view.findViewById(R.id.tietPrezimenaStudenta);
        oDatuma = (EditText)view.findViewById(R.id.etUpisDatuma);
        oBtnUpisi = (Button)view.findViewById(R.id.btnUpisi);

        oIme.addTextChangedListener(PersonalInfoWatcher);
        oPrezime.addTextChangedListener(PersonalInfoWatcher);
        oDatuma.addTextChangedListener(PersonalInfoWatcher);

        oBtnUpisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewRecordActivity.setCurrentItem (1, true);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null)
                {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }
    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_personal_info, container, false);

        imageView = v.findViewById(R.id.ivSlikaKorisnika);
        oIme = (TextInputEditText)v.findViewById(R.id.tietImeStudenta);
        oPrezime = (TextInputEditText)v.findViewById(R.id.tietPrezimenaStudenta);
        oDatuma = (EditText)v.findViewById(R.id.etUpisDatuma);
        oBtnUpisi = (Button)v.findViewById(R.id.btnUpisi);

        oIme.addTextChangedListener(PersonalInfoWatcher);
        oPrezime.addTextChangedListener(PersonalInfoWatcher);
        oDatuma.addTextChangedListener(PersonalInfoWatcher);

        oBtnUpisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewRecordActivity.setCurrentItem (1, true);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null)
                {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        return v;
    }
*/
    private TextWatcher PersonalInfoWatcher = new TextWatcher() {
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
            sImeStudenta = oIme.getText().toString();
            sPrezimeStudenta = oPrezime.getText().toString();
            sDatum = oDatuma.getText().toString();

            personalInfoListener.onPersonalInfoSent(sImeStudenta, sPrezimeStudenta, sDatum);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  PersonalInfoListener) {
            personalInfoListener = (PersonalInfoListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        personalInfoListener = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}