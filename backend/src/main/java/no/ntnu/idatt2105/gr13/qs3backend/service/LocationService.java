package no.ntnu.idatt2105.gr13.qs3backend.service;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Campus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.register.RegisterRoom;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleBuilding;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleCampus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleCampusBuilding;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleCampusBuildingRoom;
import no.ntnu.idatt2105.gr13.qs3backend.repository.location.JdbcLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LocationService {

    @Autowired
    JdbcLocationRepository locationRepository;

    public ArrayList<Campus> getLocations(){
        ArrayList<Campus> campuses = (ArrayList<Campus>) locationRepository.getLocations();
        return campuses;
    }

    public ArrayList<SimpleCampus> getCampuses(){
        ArrayList<SimpleCampus> campuses = (ArrayList<SimpleCampus>) locationRepository.getCampuses();
        return campuses;
    }

    public SimpleCampusBuilding getCampus(int id){
        SimpleCampusBuilding campus = locationRepository.getCampus(id);
        return campus;
    }

    public SimpleCampusBuildingRoom getBuilding(int id){
        return locationRepository.getBuilding(id);
    }

    public Boolean registerCampus(String name){
        return locationRepository.registerCampus(name.trim());
    }

    public Boolean registerRoom(RegisterRoom room){
        return locationRepository.registerRoom(room);
    }

    public Boolean registerBuilding(SimpleBuilding building){
        return locationRepository.registerBuilding(building);
    }
}
