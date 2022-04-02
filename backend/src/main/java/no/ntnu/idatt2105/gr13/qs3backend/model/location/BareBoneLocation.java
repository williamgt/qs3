package no.ntnu.idatt2105.gr13.qs3backend.model.location;

public class BareBoneLocation {
    private int home = 0;

    public BareBoneLocation(int home) {
        this.home = home;
    }

    public BareBoneLocation() {
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }
}
