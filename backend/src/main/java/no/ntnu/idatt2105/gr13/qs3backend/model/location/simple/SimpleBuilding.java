package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

public class SimpleBuilding {
    private String buildingName;
    private int buildingId;

    public SimpleBuilding(String buildingName, int buildingId) {
        this.buildingName = buildingName;
        this.buildingId = buildingId;
    }


    public int getId() {
        return buildingId;
    }

    public String getName() {
        return buildingName;
    }

    @Override
    public String toString() {
        return "SimpleBuilding{" +
                "buildingName='" + buildingName + '\'' +
                ", buildingId=" + buildingId +
                '}';
    }
}
