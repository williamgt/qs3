package no.ntnu.idatt2105.gr13.qs3backend.repository.location;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Campus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.register.RegisterRoom;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface for the Location repository.
 */
@Repository
public interface LocationRepository {

    /**
     * Gets locations.
     *
     * @return the locations
     */
    List<Campus> getLocations();

    /**
     * Gets campuses.
     *
     * @return the campuses
     */
    List<SimpleCampus> getCampuses();

    /**
     * Gets campus.
     *
     * @param id the id
     * @return the campus
     */
    SimpleCampusBuilding getCampus(int id);

    /**
     * Gets building.
     *
     * @param id the id
     * @return the building
     */
    SimpleCampusBuildingRoom getBuilding(int id);

    /**
     * Register campus boolean.
     *
     * @param name the name
     * @return the boolean
     */
    Boolean registerCampus(String name);

    /**
     * Register room boolean.
     *
     * @param room the room
     * @return the boolean
     */
    Boolean registerRoom(RegisterRoom room);

    /**
     * Register building boolean.
     *
     * @param building the building
     * @return the boolean
     */
    Boolean registerBuilding(SimpleBuilding building);

    /**
     * Edit campus int.
     *
     * @param campus the campus
     * @return the int
     */
    int editCampus(SimpleCampus campus);

    /**
     * Edit building int.
     *
     * @param building the building
     * @return the int
     */
    int editBuilding(SimpleBuilding building);

    /**
     * Edit room int.
     *
     * @param room the room
     * @return the int
     */
    int editRoom(SimpleRoom room);

    /**
     * Gets room.
     *
     * @param id the id
     * @return the room
     */
    SimpleRoomWBC getRoom(int id);
}
