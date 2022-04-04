package no.ntnu.idatt2105.gr13.qs3backend.model.queue;

/**
 * Models simple queue information related to a student
 */
public class SimpleStudentQueueInfo {
    private String firstname;
    private String lastname;
    private String email;
    private int queueInfoId;

    /**
     * Instantiates a new Simple student queue info.
     */
    public SimpleStudentQueueInfo() {
    }

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets firstname.
     *
     * @param firstname the firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets lastname.
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
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
