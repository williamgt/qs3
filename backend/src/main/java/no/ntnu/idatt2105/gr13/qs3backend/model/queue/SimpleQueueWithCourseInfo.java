package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

/**
 * Models the same as a SimpleQueue, but also keeps track of course information related to the queue.
 */
public class SimpleQueueWithCourseInfo extends SimpleQueue {
    private String courseCode;
    private String courseName;
    private int year;
    private int term;
    private String hashId;

    /**
     * Instantiates a new Simple queue with course info.
     */
    public SimpleQueueWithCourseInfo() {
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

    /**
     * Gets hash id.
     *
     * @return the hash id
     */
    public String getHashId() {
        return hashId;
    }

    /**
     * Sets hash id.
     *
     * @param hashId the hash id
     */
    public void setHashId(String hashId) {
        this.hashId = hashId;
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
     * Sets course name.
     *
     * @param courseName the course name
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
