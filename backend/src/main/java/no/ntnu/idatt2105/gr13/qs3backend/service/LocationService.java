package no.ntnu.idatt2105.gr13.qs3backend.service;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Campus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.register.RegisterRoom;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.*;
import no.ntnu.idatt2105.gr13.qs3backend.repository.location.JdbcLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service for LocationController
 */
@Service
public class LocationService {

    @Autowired
    JdbcLocationRepository locationRepository;

    /**
     * Get all locations stored in db
     * @return
     */
    public ArrayList<Campus> getLocations(){
        ArrayList<Campus> campuses = (ArrayList<Campus>) locationRepository.getLocations();
        return campuses;
    }

    /**
     * Get all campuses in db
     * @return
     */
    public ArrayList<SimpleCampus> getCampuses(){
        ArrayList<SimpleCampus> campuses = (ArrayList<SimpleCampus>) locationRepository.getCampuses();
        return campuses;
    }

    /**
     * All buildings and campus name for specific campus id
     * @param id
     * @return
     */
    public SimpleCampusBuilding getCampus(int id){
        SimpleCampusBuilding campus = locationRepository.getCampus(id);
        return campus;
    }

    /**
     * Get room based on id with campusID and buildingID
     * @param id
     * @return
     */
    public SimpleRoomWBC getRoom(int id){
        SimpleRoomWBC room = locationRepository.getRoom(id);
        return room;
    }

    /**
     * Get building with rooms by id
     * @param id
     * @return
     */
    public SimpleCampusBuildingRoom getBuilding(int id){
        return locationRepository.getBuilding(id);
    }

    /**
     * Register campus with given name
     * @param name
     * @return
     */
    public Boolean registerCampus(String name){
        return locationRepository.registerCampus(name.trim());
    }

    /**
     * Register room with room info and BuildingId
     * @param room
     * @return
     */
    public Boolean registerRoom(RegisterRoom room){
        return locationRepository.registerRoom(room);
    }

    /**
     * Register building (Name and campusID)
     * @param building
     * @return
     */
    public Boolean registerBuilding(SimpleBuilding building){
        return locationRepository.registerBuilding(building);
    }

    /**
     * Edit name of campus
     * @param campus
     * @return
     */
    public boolean editCampus(SimpleCampus campus){
        return locationRepository.editCampus(campus) == 1;
    }

    /**
     * Edit name of building
     * @param building
     * @return
     */
    public boolean editBuilding(SimpleBuilding building){
        return locationRepository.editBuilding(building) == 1;
    }

    /**
     * Edit Room
     * @param room
     * @return
     */
    public boolean editRoom(SimpleRoom room){
        return locationRepository.editRoom(room) == 1;
    }
}
