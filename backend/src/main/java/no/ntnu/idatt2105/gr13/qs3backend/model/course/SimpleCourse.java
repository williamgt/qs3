package no.ntnu.idatt2105.gr13.qs3backend.model.course;

public class SimpleCourse {
    private String courseCode;
    private int year;
    private int term;

    public SimpleCourse() {
    }

    public SimpleCourse(String courseCode, String year, String term) {
        this.courseCode = courseCode;
        this.year = Integer.parseInt(year);
        this.term = Integer.parseInt((term));
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
