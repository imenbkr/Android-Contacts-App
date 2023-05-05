package com.example.homework;

import java.io.Serializable;

public class MyObject  {
    private int id;
    private String name;
    private int number;

    public MyObject( String name, int number) {
        this.name = name;
        this.number = number;
    }
    public MyObject(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setId(int id) {
        this.id = id;
    }
}
