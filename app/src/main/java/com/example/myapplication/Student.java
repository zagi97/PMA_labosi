package com.example.myapplication;

public class Student {

    private String Ime;
    private String Prezime;
    private String Predmet;

    public Student(String ime, String prezime, String predmet)
    {
        Ime = ime;
        Prezime = prezime;
        Predmet = predmet;
    }

    public String getIme()
    {
        return Ime;
    }

    public String getPrezime()
    {
        return Prezime;
    }

    public String getPredmet()
    {
        return Predmet;
    }
}
