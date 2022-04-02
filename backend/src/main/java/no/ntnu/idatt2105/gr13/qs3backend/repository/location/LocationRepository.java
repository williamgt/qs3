package no.ntnu.idatt2105.gr13.qs3backend.repository.location;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Campus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.single.SimpleCampus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository {

    List<Campus> getLocations();

    List<SimpleCampus> getCampuses();

    SimpleCampus getCampus(int id);
}
