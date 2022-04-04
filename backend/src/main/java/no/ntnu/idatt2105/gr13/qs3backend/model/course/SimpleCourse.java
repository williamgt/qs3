package no.ntnu.idatt2105.gr13.qs3backend.model.course;

/**
 * Models a course with all it's basic information
 */
public class SimpleCourse {
    private String courseCode;
    private int year;
    private int term;

    /**
     * Instantiates a new Simple course.
     */
    public SimpleCourse() {
    }

    /**
     * Instantiates a new Simple course.
     *
     * @param courseCode the course code
     * @param year       the year
     * @param term       the term
     */
    public SimpleCourse(String courseCode, String year, String term) {
        this.courseCode = courseCode;
        this.year = Integer.parseInt(year);
        this.term = Integer.parseInt((term));
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
}
