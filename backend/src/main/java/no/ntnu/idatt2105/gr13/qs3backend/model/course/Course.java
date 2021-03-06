package no.ntnu.idatt2105.gr13.qs3backend.model.course;


import no.ntnu.idatt2105.gr13.qs3backend.model.queue.Queue;
import no.ntnu.idatt2105.gr13.qs3backend.model.task.Task;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.StudentUser;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.TAUser;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.TeacherUser;

import java.util.List;

/**
 * Models a course and all it's related information
 */
public class Course {
    private int year;
    private int term;
    private String courseCode;
    private String courseName;

    private List<TeacherUser> teachers;
    private List<TAUser> tas;
    private List<StudentUser> students;

    public Course(int year) {
        if(year < 0){
            throw new IllegalArgumentException("Year can't be negative");
        }
        this.year = year;
    }

    private Queue queue;

    private int obligatoryTaskAmount;
    private int setOfTasks;
    private List<List<Task>> tasksInEachSet;
    //private List<Integer> obligatoryPerSet;
    private int[] obligatoryPerSet;

    /**
     * Instantiates a new Course.
     */
    public Course() {
    }

    /**
     * Instantiates a new Course.
     *
     * @param year                 the year
     * @param term                 the term
     * @param courseCode           the course code
     * @param courseName           the course name
     * @param teachers             the teachers
     * @param tas                  the tas
     * @param students             the students
     * @param queue                the queue
     * @param obligatoryTaskAmount the obligatory task amount
     * @param setOfTasks           the set of tasks
     * @param tasksInEachSet       the tasks in each set
     * @param obligatoryPerSet     the obligatory per set
     */
    public Course(int year, int term, String courseCode, String courseName, List<TeacherUser> teachers, List<TAUser> tas, List<StudentUser> students, Queue queue, int obligatoryTaskAmount, int setOfTasks, List<List<Task>> tasksInEachSet, int[] obligatoryPerSet) {
        if(year < 0){
            throw new IllegalArgumentException("Year can't be negative");
        }
        this.year = year;

        if(2 < term || term < 1){
            throw new IllegalArgumentException("Term must be one or two");
        }
        this.term = term;


        this.courseCode = courseCode.trim().toUpperCase();

        if(courseName.isEmpty()){
            throw new IllegalArgumentException("Course name can't be empty");
        }
        this.courseName = courseName;

        this.teachers = teachers;
        this.tas = tas;
        this.students = students;
        this.queue = queue;
        if(obligatoryTaskAmount < 0){
            throw new IllegalArgumentException("Obligatory task amount can't be less than 0");
        }
        this.obligatoryTaskAmount = obligatoryTaskAmount;
        this.setOfTasks = setOfTasks;
        this.tasksInEachSet = tasksInEachSet;
        this.obligatoryPerSet = obligatoryPerSet;
    }

    /**
     * Gets course name.
     *
     * @return the course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Gets queue.
     *
     * @return the queue
     */
    public Queue getQueue() {
        return queue;
    }

    /**
     * Gets obligatory task amount.
     *
     * @return the obligatory task amount
     */
    public int getObligatoryTaskAmount() {
        return obligatoryTaskAmount;
    }

    /**
     * Gets set of tasks.
     *
     * @return the set of tasks
     */
    public int getSetOfTasks() {
        return setOfTasks;
    }

    /**
     * Gets tasks in each set.
     *
     * @return the tasks in each set
     */
    public List<List<Task>> getTasksInEachSet() {
        return tasksInEachSet;
    }

    /**
     * Get obligatory per set int [ ].
     *
     * @return the int [ ]
     */
    public int[] getObligatoryPerSet() {
        return obligatoryPerSet;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets term.
     *
     * @return the term
     */
    public int getTerm() {
        return term;
    }

    /**
     * Gets course code.
     *
     * @return the course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Gets teachers.
     *
     * @return the teachers
     */
    public List<TeacherUser> getTeachers() {
        return teachers;
    }

    /**
     * Gets tas.
     *
     * @return the tas
     */
    public List<TAUser> getTas() {
        return tas;
    }

    /**
     * Gets students.
     *
     * @return the students
     */
    public List<StudentUser> getStudents() {
        return students;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        if(year < 0){
            throw new IllegalArgumentException("Year can't be negative");
        }
        this.year = year;
    }

    /**
     * Sets term.
     *
     * @param term the term
     */
    public void setTerm(int term) {
        if(2 < term || term < 1){
            throw new IllegalArgumentException("Term must be one or two");
        }
        this.term = term;
    }

    /**
     * Sets course code.
     *
     * @param courseCode the course code
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode.toUpperCase();
    }

    /**
     * Sets teachers.
     *
     * @param teachers the teachers
     */
    public void setTeachers(List<TeacherUser> teachers) {
        this.teachers = teachers;
    }

    /**
     * Sets tas.
     *
     * @param tas the tas
     */
    public void setTas(List<TAUser> tas) {
        this.tas = tas;
    }

    /**
     * Sets students.
     *
     * @param students the students
     */
    public void setStudents(List<StudentUser> students) {
        this.students = students;
    }

    /**
     * Sets course name.
     *
     * @param courseName the course name
     */
    public void setCourseName(String courseName) {
        if(courseName.isEmpty()){
            throw new IllegalArgumentException("Course name can't be empty");
        }
        this.courseName = courseName;
    }

    /**
     * Sets queue.
     *
     * @param queue the queue
     */
    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    /**
     * Sets obligatory task amount.
     *
     * @param obligatoryTaskAmount the obligatory task amount
     */
    public void setObligatoryTaskAmount(int obligatoryTaskAmount) {
        if(obligatoryTaskAmount < 0){
            throw new IllegalArgumentException("Obligatory task amount can't be less than 0");
        }
        this.obligatoryTaskAmount = obligatoryTaskAmount;
    }

    /**
     * Sets set of tasks.
     *
     * @param setOfTasks the set of tasks
     */
    public void setSetOfTasks(int setOfTasks) {
        this.setOfTasks = setOfTasks;
    }

    /**
     * Sets tasks in each set.
     *
     * @param tasksInEachSet the tasks in each set
     */
    public void setTasksInEachSet(List<List<Task>> tasksInEachSet) {
        this.tasksInEachSet = tasksInEachSet;
    }

    /**
     * Sets obligatory per set.
     *
     * @param obligatoryPerSet the obligatory per set
     */
    public void setObligatoryPerSet(int[] obligatoryPerSet) {
        this.obligatoryPerSet = obligatoryPerSet;
    }


}
