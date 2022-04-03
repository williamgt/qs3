package no.ntnu.idatt2105.gr13.qs3backend.model.course;

public class SimpleCourseWithName extends SimpleCourse {
    private String courseName;
    private String hashId;

    public SimpleCourseWithName(String courseCode, String year, String term, String courseName, String hashId) {
        super(courseCode, year, term);
        this.courseName = courseName;
        this.hashId = hashId;
    }

    public SimpleCourseWithName() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }
}
