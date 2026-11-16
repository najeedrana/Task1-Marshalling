package com.webservices.model;

import jakarta.xml.bind.annotation.*;

/** A dependent object representing one subject in a student's record. */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "grade", "passed"})
public class Subject {
    // Fields describe the subject. JAXB maps them to XML elements.
    private int id;
    private String name;
    private float grade;
    private boolean passed;

    /** JAXB requires a public no-argument constructor when rebuilding objects from XML. */
    public Subject() { }

    /** Convenience constructor used when creating sample subject objects. */
    public Subject(int id, String name, float grade, boolean passed) {
        this.id = id; this.name = name; this.grade = grade; this.passed = passed;
    }

    /** Getter and setter methods expose private fields in a controlled way. */
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public float getGrade() { return grade; }
    public void setGrade(float grade) { this.grade = grade; }
    public boolean isPassed() { return passed; }
    public void setPassed(boolean passed) { this.passed = passed; }
}
