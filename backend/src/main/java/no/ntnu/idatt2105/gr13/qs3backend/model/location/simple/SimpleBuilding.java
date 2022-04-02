package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

public class SimpleBuilding {
    int id;
    String name;

    public SimpleBuilding(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
