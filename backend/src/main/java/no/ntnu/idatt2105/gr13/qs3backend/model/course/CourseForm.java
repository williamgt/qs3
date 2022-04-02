package no.ntnu.idatt2105.gr13.qs3backend.model.course;

import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.StudentUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.TAUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.TeacherUserBasic;

import java.util.List;

public class CourseForm {
    private int year;
    private int term;
    private String courseCode;
    private String courseName;
    private List<TeacherUserBasic> teachers;
    private List<TAUserBasic> tas;
    private List<StudentUserBasic> students;
    private int obligatoryTaskAmount;
    private int setOfTasks;
    private List<List<Task>> tasksInEachSet;
    private List<Integer> obligatoryPerSet;

    public CourseForm() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public List<StudentUserBasic> getStudents() {
        return students;
    }

    public void setStudents(List<StudentUserBasic> students) {
        this.students = students;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<TeacherUserBasic> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherUserBasic> teachers) {
        this.teachers = teachers;
    }

    public List<TAUserBasic> getTas() {
        return tas;
    }

    public void setTas(List<TAUserBasic> tas) {
        this.tas = tas;
    }


    public int getObligatoryTaskAmount() {
        return obligatoryTaskAmount;
    }

    public void setObligatoryTaskAmount(int obligatoryTaskAmount) {
        this.obligatoryTaskAmount = obligatoryTaskAmount;
    }

    public int getSetOfTasks() {
        return setOfTasks;
    }

    public void setSetOfTasks(int setOfTasks) {
        this.setOfTasks = setOfTasks;
    }

    public List<List<Task>> getTasksInEachSet() {
        return tasksInEachSet;
    }

    public void setTasksInEachSet(List<List<Task>> tasksInEachSet) {
        this.tasksInEachSet = tasksInEachSet;
    }

    public List<Integer> getObligatoryPerSet() {
        return obligatoryPerSet;
    }

    public void setObligatoryPerSet(List<Integer> obligatoryPerSet) {
        this.obligatoryPerSet = obligatoryPerSet;
    }

    @Override
    public String toString() {
        return "CourseForm{" +
                "year=" + year +
                ", term=" + term +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teachers=" + teachers +
                ", tas=" + tas +
                ", students=" + students +
                ", obligatoryTaskAmount=" + obligatoryTaskAmount +
                ", setOfTasks=" + setOfTasks +
                ", tasksInEachSet=" + tasksInEachSet +
                ", obligatoryPerSet=" + obligatoryPerSet +
                '}';
    }
}
