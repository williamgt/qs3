package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.BareBoneLocation;

/**
 * Model representing a location with possibility of setting a flag for home
 */
public class SimpleLocationHome extends BareBoneLocation {
    /**
     * Instantiates a new Simple location home.
     *
     * @param home int representing if home (1) or not (0)
     */
    public SimpleLocationHome(int home) {
        super(home);
    }


    /**
     * Instantiates a new, empty Simple location home.
     */
    public SimpleLocationHome() {
    }
}
