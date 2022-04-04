package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

/**
 * Models a Campus with its name and corresponding ID found in DB
 */
public class SimpleCampus {
    private String campusName;
    private int campusId;


    /**
     * Instantiates a new Simple campus.
     *
     * @param campusName the campus name
     * @param campusId   the campus id
     */
    public SimpleCampus(String campusName, int campusId) {
        this.campusName = campusName;
        this.campusId = campusId;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return campusId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return campusName;
    }
}
