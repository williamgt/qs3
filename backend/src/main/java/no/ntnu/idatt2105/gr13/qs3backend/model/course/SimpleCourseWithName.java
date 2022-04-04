package no.ntnu.idatt2105.gr13.qs3backend.model.course;

/**
 * Models a course with it's hashID and name
 */
public class SimpleCourseWithName extends SimpleCourse {
    private String courseName;
    private String hashId;

    /**
     * Instantiates a new Simple course with name.
     *
     * @param courseCode the course code
     * @param year       the year
     * @param term       the term
     * @param courseName the course name
     * @param hashId     the hash id
     */
    public SimpleCourseWithName(String courseCode, String year, String term, String courseName, String hashId) {
        super(courseCode, year, term);
        this.courseName = courseName;
        this.hashId = hashId;
    }

    /**
     * Instantiates a new Simple course with name.
     */
    public SimpleCourseWithName() {
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
}
