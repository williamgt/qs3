package no.ntnu.idatt2105.gr13.qs3backend.model;

import no.ntnu.idatt2105.gr13.qs3backend.model.person.Student;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.Teacher;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.TeacherAssistant;

import java.util.ArrayList;

public class Course {
    int year;
    String courseCode;
    ArrayList<Teacher> teachers;
    ArrayList<TeacherAssistant> tas;
    ArrayList<Student> students;
    //boolean active; //This is not the queue

    public int getYear() {
        return year;
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
}
