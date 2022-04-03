package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

public class SimpleCampus {
    private int id;
    private String name;

    public SimpleCampus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SimpleCampus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
