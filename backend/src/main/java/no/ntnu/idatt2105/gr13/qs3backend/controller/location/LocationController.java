package no.ntnu.idatt2105.gr13.qs3backend.controller.location;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Campus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.register.RegisterRoom;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleBuilding;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleCampus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleCampusBuilding;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleCampusBuildingRoom;
import no.ntnu.idatt2105.gr13.qs3backend.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/locations")
@CrossOrigin("*")
public class LocationController {
    Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @GetMapping("")
    public ArrayList<Campus> getLocations(){
        logger.info("Retrieved all locations");
        return locationService.getLocations();
    }

    @GetMapping("/campus")
    public ArrayList<SimpleCampus> getCampuses(){
        logger.info("Retrieved all campuses");
        return locationService.getCampuses();
    }

    @GetMapping("/campus/{id}")
    public SimpleCampusBuilding getCampus(@PathVariable("id") long id){
        SimpleCampusBuilding c = locationService.getCampus((int) id);
        logger.info("Retrieved all info from campus: " + c.toString());
        return c;
    }

    @GetMapping("/building/{id}")
    public SimpleCampusBuildingRoom getBuilding(@PathVariable("id") long id){
        SimpleCampusBuildingRoom c = locationService.getBuilding((int) id);
        logger.info("Retrieved all info from building: " + c.getBuilding().toString());
        return c;
    }

    @PostMapping("/campus")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity registerCampus(@RequestBody String name){
        if(locationService.registerCampus(name)){
            logger.info("Registered campus: " +name);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        logger.info("Failed to register campus: " + name);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/room")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity registerRoom(@RequestBody RegisterRoom room){
        if(locationService.registerRoom(room)){
            logger.info("Registered room: " + room);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        logger.error("Failed to register room: " + room);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/building")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity registerBuilding(@RequestBody SimpleBuilding building){
        if(locationService.registerBuilding(building)){
            logger.info("Registered building: " + building);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        logger.error("Failed to register building: " + building);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
