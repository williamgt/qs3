package no.ntnu.idatt2105.gr13.qs3backend.model.location;

/**
 * Model for location which is home or not
 */
public class BareBoneLocation {
    private int home = 0;

    /**
     * Constructor for model
     * @param home
     */
    public BareBoneLocation(int home) {
        this.home = home;
    }

    /**
     * Empty constructor
     */
    public BareBoneLocation() {
    }

    /**
     * Method for getting home
     * @return
     */
    public int getHome() {
        return home;
    }

    /**
     * Method for setting home
     * @param home
     */
    public void setHome(int home) {
        this.home = home;
    }
}
