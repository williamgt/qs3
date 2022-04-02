package no.ntnu.idatt2105.gr13.qs3backend.model.location;

import java.util.ArrayList;

public class Campus {
    private ArrayList<Building> buildings = null;
    private String campusName;
    private int campusId;

    public Campus(String campusName, int campusId) {
        this.campusName = campusName;
        this.campusId = campusId;
    }
}
