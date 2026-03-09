package com.airtribe.meditrack.entity;

public class Patient extends Person implements Cloneable {

    private String disease;

    public Patient(String id, String name, int age, String disease) {
        super(id, name, age);
        this.disease = disease;
    }

    public String getDisease() { return disease; }

    @Override
    public Patient clone() {
        return new Patient(id, name, age, disease); // deep copy example
    }

    @Override
    public void display() {
        System.out.println("Patient: " + name + " | Disease: " + disease);
    }
}