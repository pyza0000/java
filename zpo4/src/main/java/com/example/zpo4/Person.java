package com.example.zpo4;

public class Person {

    private String name;
    private int age;
    private boolean active;
    private Double salary;
    private Character grade;
    private String descriptionText;

    public Person() {
        name = "Jan";
        age = 20;
        active = true;
        salary = 5000.0;
        grade = 'A';
        descriptionText = "Przykładowy tekst";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Character getGrade() {
        return grade;
    }

    public void setGrade(Character grade) {
        this.grade = grade;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }
}