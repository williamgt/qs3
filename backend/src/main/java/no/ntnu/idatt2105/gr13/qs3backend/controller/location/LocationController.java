package no.ntnu.idatt2105.gr13.qs3backend.controller.location;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Campus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.single.SimpleCampus;
import no.ntnu.idatt2105.gr13.qs3backend.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SimpleCampus getCampus(@PathVariable("id") long id){
        logger.info("Retrieved all campuses");
        return locationService.getCampus((int) id);
    }
}
