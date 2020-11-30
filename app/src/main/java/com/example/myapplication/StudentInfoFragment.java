package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class StudentInfoFragment extends Fragment implements Callback<CourseResponse>, AdapterView.OnItemSelectedListener{

    private static final String TAG = "MyActivity";
    private CourseResponse courseResponse = new CourseResponse();
    private ArrayList<Courses> courses = new ArrayList<>();
    private ArrayList<String> predmeti = new ArrayList<>();
    private ArrayList<Instructor> instruktori = new ArrayList<>();
    private ArrayList<String> naziv_instruktora = new ArrayList<>();
    private String sImeStudenta;
    private String sPrezimeStudenta;
    private String sDatumRođenja;

    private String sNazivPredmeta;
    private String sNazivProfesora;
    private String sAkademskaGodina;
    private String sBrojSatiPredavanja;
    private String sBrojSatiLabosa;
    //private TextInputEditText oUpisiPredmet;
    //private TextInputEditText oUpisiProfesora;
    private TextInputEditText oUpisiAkademskuGodinu;
    private TextInputEditText oUpisiBrojSatiPredavanja;
    private TextInputEditText oUpisiBrojSatiLabosa;
    private Spinner oSpPredmet;
    private Spinner oSpProfesor;
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

        //oUpisiPredmet = (TextInputEditText)v.findViewById(R.id.tietNazivPredmeta);
        //oUpisiProfesora = (TextInputEditText)v.findViewById(R.id.tietNazivProfesora);
        oUpisiAkademskuGodinu = (TextInputEditText)v.findViewById(R.id.tietUpisAkademskeGodine);
        oUpisiBrojSatiPredavanja = (TextInputEditText)v.findViewById(R.id.tietUpisSatiPredavanja);
        oUpisiBrojSatiLabosa = (TextInputEditText)v.findViewById(R.id.tietUpisSatiLabosa);
        oSpPredmet = (Spinner)v.findViewById(R.id.spOdabirPredmeta);
        oSpProfesor = (Spinner)v.findViewById(R.id.spUpisProfesora);
        oBtnUpisiPredmet = (Button)v.findViewById(R.id.btnUpisPredmeta);

        //oUpisiPredmet.addTextChangedListener(StudentInfoWatcher);
        //oUpisiProfesora.addTextChangedListener(StudentInfoWatcher);
        oUpisiAkademskuGodinu.addTextChangedListener(StudentInfoWatcher);
        oUpisiBrojSatiPredavanja.addTextChangedListener(StudentInfoWatcher);
        oUpisiBrojSatiLabosa.addTextChangedListener(StudentInfoWatcher);

        oBtnUpisiPredmet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewRecordActivity.setCurrentItem (2, true);
            }
        });

        ApiManager.getInstance().service().getCourses().enqueue(this);

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
            //sNazivPredmeta = oUpisiPredmet.getText().toString();
            //sNazivProfesora = oUpisiProfesora.getText().toString();
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

    @Override
    public void onResponse(@NonNull Call<CourseResponse> call, @NonNull Response<CourseResponse> response)
    {
        if (response.isSuccessful() && response.body() != null)
        {
            courseResponse = response.body();
            courses = courseResponse.getCourses();

            for(Courses course : courses)
            {
                if(course.getTitle() !=null && !course.getTitle().matches(""))
                {
                    predmeti.add(course.getTitle());
                }
            }
            ArrayAdapter<String> adapterPredmet = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, predmeti);
            oSpPredmet.setAdapter(adapterPredmet);
            oSpPredmet.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onFailure(Call<CourseResponse> call, Throwable t)
    {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        if(adapterView.getId() == R.id.spOdabirPredmeta)
        {
            Log.d(TAG, "onItemSelected: " + oSpPredmet.getSelectedItem());

            for(Courses course : courses){
                if(oSpPredmet.getSelectedItem() == course.getTitle())
                {
                    instruktori = course.getInstructors();

                    if(instruktori != null)
                    {
                        naziv_instruktora.clear();

                        for(Instructor instructor : instruktori)
                        {
                            naziv_instruktora.add(instructor.getName());

                            Log.d(TAG, "onResponse: " + instructor.getName());
                        }
                    }
                }
            }

            ArrayAdapter<String> adapterProfesor = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, naziv_instruktora);
            oSpProfesor.setAdapter(adapterProfesor);
            oSpProfesor.setOnItemSelectedListener(this);

            sNazivPredmeta = oSpPredmet.getSelectedItem().toString();
            studentInfoListener.onStudentInfoSent(sNazivPredmeta, sNazivProfesora, sAkademskaGodina, sBrojSatiPredavanja, sBrojSatiLabosa);
        }
        else if(adapterView.getId() == R.id.spUpisProfesora)
        {
            sNazivProfesora = oSpProfesor.getSelectedItem().toString();
            studentInfoListener.onStudentInfoSent(sNazivPredmeta, sNazivProfesora, sAkademskaGodina, sBrojSatiPredavanja, sBrojSatiLabosa);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }

}