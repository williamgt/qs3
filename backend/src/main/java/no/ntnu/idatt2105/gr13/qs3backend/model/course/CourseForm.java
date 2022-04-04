package no.ntnu.idatt2105.gr13.qs3backend.model.course;

import no.ntnu.idatt2105.gr13.qs3backend.model.task.TaskFromForm;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.StudentUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.TAUserBasic;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.basics.TeacherUserBasic;

import java.util.List;

/**
 * Models a form containing information about a course to be registered
 */
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
    private List<List<TaskFromForm>> tasksInEachSet;
    private List<Integer> obligatoryPerSet;

    /**
     * Instantiates a new Course form.
     */
    public CourseForm() {
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
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
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
     * Sets term.
     *
     * @param term the term
     */
    public void setTerm(int term) {
        this.term = term;
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
     * Sets course code.
     *
     * @param courseCode the course code
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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
     * Gets students.
     *
     * @return the students
     */
    public List<StudentUserBasic> getStudents() {
        return students;
    }

    /**
     * Sets students.
     *
     * @param students the students
     */
    public void setStudents(List<StudentUserBasic> students) {
        this.students = students;
    }

    /**
     * Sets course name.
     *
     * @param courseName the course name
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Gets teachers.
     *
     * @return the teachers
     */
    public List<TeacherUserBasic> getTeachers() {
        return teachers;
    }

    /**
     * Sets teachers.
     *
     * @param teachers the teachers
     */
    public void setTeachers(List<TeacherUserBasic> teachers) {
        this.teachers = teachers;
    }

    /**
     * Gets tas.
     *
     * @return the tas
     */
    public List<TAUserBasic> getTas() {
        return tas;
    }

    /**
     * Sets tas.
     *
     * @param tas the tas
     */
    public void setTas(List<TAUserBasic> tas) {
        this.tas = tas;
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
     * Sets obligatory task amount.
     *
     * @param obligatoryTaskAmount the obligatory task amount
     */
    public void setObligatoryTaskAmount(int obligatoryTaskAmount) {
        this.obligatoryTaskAmount = obligatoryTaskAmount;
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
     * Sets set of tasks.
     *
     * @param setOfTasks the set of tasks
     */
    public void setSetOfTasks(int setOfTasks) {
        this.setOfTasks = setOfTasks;
    }

    /**
     * Gets tasks in each set.
     *
     * @return the tasks in each set
     */
    public List<List<TaskFromForm>> getTasksInEachSet() {
        return tasksInEachSet;
    }

    /**
     * Sets tasks in each set.
     *
     * @param tasksInEachSet the tasks in each set
     */
    public void setTasksInEachSet(List<List<TaskFromForm>> tasksInEachSet) {
        this.tasksInEachSet = tasksInEachSet;
    }

    /**
     * Gets obligatory per set.
     *
     * @return the obligatory per set
     */
    public List<Integer> getObligatoryPerSet() {
        return obligatoryPerSet;
    }

    /**
     * Sets obligatory per set.
     *
     * @param obligatoryPerSet the obligatory per set
     */
    public void setObligatoryPerSet(List<Integer> obligatoryPerSet) {
        this.obligatoryPerSet = obligatoryPerSet;
    }
}
