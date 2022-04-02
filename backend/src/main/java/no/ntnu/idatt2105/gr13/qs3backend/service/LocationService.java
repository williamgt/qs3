package no.ntnu.idatt2105.gr13.qs3backend.service;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Campus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.single.SimpleCampus;
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

    public SimpleCampus getCampus(int id){
        SimpleCampus campus = locationRepository.getCampus(id);
        return campus;
    }
}
