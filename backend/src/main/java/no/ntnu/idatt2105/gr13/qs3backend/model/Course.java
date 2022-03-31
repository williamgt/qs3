package no.ntnu.idatt2105.gr13.qs3backend.model;

import no.ntnu.idatt2105.gr13.qs3backend.model.person.Student;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.Teacher;
import no.ntnu.idatt2105.gr13.qs3backend.model.person.TeacherAssistant;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;

import java.util.List;

public class Course {
    private int year;
    private int term;
    private String courseCode;
    private String courseName;

    private List<Teacher> teachers;
    private List<TeacherAssistant> tas;
    private List<Student> students;
    //boolean active; //This is not the queue

    private Queue queue;

    private int obligatoryTaskAmount;
    private int setOfTasks;
    private List<List<Task>> tasksInEachSet;
    //private List<Integer> obligatoryPerSet;
    private int[] obligatoryPerSet = new int[setOfTasks];

    public Course() {
    }

    public Course(int year, int term, String courseCode, String courseName, List<Teacher> teachers, List<TeacherAssistant> tas, List<Student> students, Queue queue, int obligatoryTaskAmount, int setOfTasks, List<List<Task>> tasksInEachSet, int[] obligatoryPerSet) {
        this.year = year;
        this.term = term;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.teachers = teachers;
        this.tas = tas;
        this.students = students;
        this.queue = queue;
        this.obligatoryTaskAmount = obligatoryTaskAmount;
        this.setOfTasks = setOfTasks;
        this.tasksInEachSet = tasksInEachSet;
        this.obligatoryPerSet = obligatoryPerSet;
    }

    public String getCourseName() {
        return courseName;
    }

    public Queue getQueue() {
        return queue;
    }

    public int getObligatoryTaskAmount() {
        return obligatoryTaskAmount;
    }

    public int getSetOfTasks() {
        return setOfTasks;
    }

    public List<List<Task>> getTasksInEachSet() {
        return tasksInEachSet;
    }

    public int[] getObligatoryPerSet() {
        return obligatoryPerSet;
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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<TeacherAssistant> getTas() {
        return tas;
    }

    public List<Student> getStudents() {
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

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void setTas(List<TeacherAssistant> tas) {
        this.tas = tas;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void setObligatoryTaskAmount(int obligatoryTaskAmount) {
        this.obligatoryTaskAmount = obligatoryTaskAmount;
    }

    public void setSetOfTasks(int setOfTasks) {
        this.setOfTasks = setOfTasks;
    }

    public void setTasksInEachSet(List<List<Task>> tasksInEachSet) {
        this.tasksInEachSet = tasksInEachSet;
    }

    public void setObligatoryPerSet(int[] obligatoryPerSet) {
        this.obligatoryPerSet = obligatoryPerSet;
    }
}
