package no.ntnu.idatt2105.gr13.qs3backend.model.user;

/**
 * Models a student user with ID related to the users' position in a queue.
 */
public class StudentUser extends User {
    private int queueInfoId;

    /**
     * Instantiates a new Student user.
     *
     * @param email       the email
     * @param firstName   the first name
     * @param lastName    the last name
     * @param queueInfoId the queue info id
     */
    public StudentUser(String email, String firstName, String lastName, int queueInfoId) {
        super(email, firstName, lastName);
        this.queueInfoId = queueInfoId;
    }

    /**
     * Gets queue info id.
     *
     * @return the queue info id
     */
    public int getQueueInfoId() {
        return queueInfoId;
    }

    /**
     * Sets queue info id.
     *
     * @param queueInfoId the queue info id
     */
    public void setQueueInfoId(int queueInfoId) {
        this.queueInfoId = queueInfoId;
    }
}
