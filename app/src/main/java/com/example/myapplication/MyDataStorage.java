package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class MyDataStorage {

    private List<Object> students;
    private MyDataStorage()
    {
        students = new ArrayList<Object>();
    }
    static private MyDataStorage instanca;

    static MyDataStorage getInstance()
    {
        if(instanca==null)
        {
            instanca = new MyDataStorage();
            instanca.students.add("Studenti");
        }
        return instanca;
    }

    public void setStudents(Student student)
    {
        this.students.add(student);
    }

    public List<Object> getStudents()
    {
        return students;
    }
}
