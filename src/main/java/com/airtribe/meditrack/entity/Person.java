package com.airtribe.meditrack.entity;

public abstract class Person extends medicalEntity {

    protected String name;
    protected int age;

    public Person(String id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
}