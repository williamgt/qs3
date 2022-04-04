package no.ntnu.idatt2105.gr13.qs3backend.model.user;

public class StudentUser extends User {
    private int queueInfoId;

    public StudentUser(String email, String firstName, String lastName, int queueInfoId) {
        super(email, firstName, lastName);
        this.queueInfoId = queueInfoId;
    }

    public int getQueueInfoId() {
        return queueInfoId;
    }

    public void setQueueInfoId(int queueInfoId) {
        this.queueInfoId = queueInfoId;
    }
}
