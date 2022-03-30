package no.ntnu.idatt2105.gr13.qs3backend.model;

import no.ntnu.idatt2105.gr13.qs3backend.model.person.Student;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.Teacher;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.TeacherAssistant;

import java.util.ArrayList;

public class Course {
    int year;
    int term;
    String courseCode;

    ArrayList<Teacher> teachers;
    ArrayList<TeacherAssistant> tas;
    ArrayList<Student> students;
    //boolean active; //This is not the queue


    public Course() {
    }

    public Course(int year, int term, String courseCode, ArrayList<Teacher> teachers, ArrayList<TeacherAssistant> tas, ArrayList<Student> students) {
        this.year = year;
        this.term = term;
        this.courseCode = courseCode;
        this.teachers = teachers;
        this.tas = tas;
        this.students = students;
    }

    public int getYear() {
        return year;
    }

    public int getTerm() {
        return term;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public ArrayList<TeacherAssistant> getTas() {
        return tas;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void setTas(ArrayList<TeacherAssistant> tas) {
        this.tas = tas;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
