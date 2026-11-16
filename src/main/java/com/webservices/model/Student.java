package com.webservices.model;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/** Root POJO in the XML document; it contains a list of dependent Subject objects. */
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "age", "averageGrade", "active", "middleInitial", "subjects"})
public class Student {
    // Demonstrates the different field types requested in the assessment.
    private int id;
    private String name;
    private int age;
    private float averageGrade;
    private boolean active;
    private char middleInitial;

    // JAXB writes the list inside <subjects>, with one <subject> per list item.
    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private List<Subject> subjects = new ArrayList<>();

    /** Required by JAXB during unmarshalling. */
    public Student() { }

    /** Creates a Student with its basic details. */
    public Student(int id, String name, int age, float averageGrade, boolean active, char middleInitial) {
        this.id=id; this.name=name; this.age=age; this.averageGrade=averageGrade;
        this.active=active; this.middleInitial=middleInitial;
    }

    public int getId(){return id;} public void setId(int v){id=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public int getAge(){return age;} public void setAge(int v){age=v;}
    public float getAverageGrade(){return averageGrade;} public void setAverageGrade(float v){averageGrade=v;}
    public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
    public char getMiddleInitial(){return middleInitial;} public void setMiddleInitial(char v){middleInitial=v;}
    public List<Subject> getSubjects(){return subjects;} public void setSubjects(List<Subject> v){subjects=v;}
}
