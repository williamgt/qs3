package no.ntnu.idatt2105.gr13.qs3backend.repository.location;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Campus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.register.RegisterRoom;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleBuilding;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleCampus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleCampusBuilding;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.SimpleCampusBuildingRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository {

    List<Campus> getLocations();

    List<SimpleCampus> getCampuses();

    SimpleCampusBuilding getCampus(int id);

    SimpleCampusBuildingRoom getBuilding(int id);

    Boolean registerCampus(String name);

    Boolean registerRoom(RegisterRoom room);

    Boolean registerBuilding(SimpleBuilding building);
}
