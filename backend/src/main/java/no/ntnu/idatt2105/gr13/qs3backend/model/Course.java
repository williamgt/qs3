package no.ntnu.idatt2105.gr13.qs3backend.model;

import no.ntnu.idatt2105.gr13.qs3backend.model.person.Student;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.Teacher;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.TeacherAssistant;

import java.util.ArrayList;

public class Course {
    int year;
    String courseId;
    ArrayList<Teacher> teachers;
    ArrayList<TeacherAssistant> tas;
    ArrayList<Student> students;
}
