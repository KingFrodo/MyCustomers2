package com.example.mycustomers;

import java.util.LinkedList;
import java.util.List;

public class Customer {
    public int id;
    public String vorname;
    public String nachname;

    public Customer(int id, String vorname, String nachname){
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public int getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Override
    public String toString() {
        return vorname + ", " + nachname;
    }
}
