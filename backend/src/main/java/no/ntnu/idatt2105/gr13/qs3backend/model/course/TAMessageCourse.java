package no.ntnu.idatt2105.gr13.qs3backend.model.course;

public class TAMessageCourse {
    private String courseCode;
    private int year;
    private int term;
    private String message;

    public TAMessageCourse() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getYear() {
        return year;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
