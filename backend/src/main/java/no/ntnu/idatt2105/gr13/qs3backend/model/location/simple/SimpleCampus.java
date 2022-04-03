package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

public class SimpleCampus {
    private String campusName;
    private int campusId;

    public SimpleCampus(String campusName, int campusId) {
        this.campusName = campusName;
        this.campusId = campusId;
    }

    public int getId() {
        return campusId;
    }

    public String getName() {
        return campusName;
    }

    @Override
    public String toString() {
        return "SimpleCampus{" +
                "id=" + campusId +
                ", name='" + campusName + '\'' +
                '}';
    }
}
