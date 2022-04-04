package no.ntnu.idatt2105.gr13.qs3backend.service;

import no.ntnu.idatt2105.gr13.qs3backend.repository.location.JdbcLocationRepository;
import no.ntnu.idatt2105.gr13.qs3backend.service.location.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @InjectMocks
    private LocationService service;

    @Mock
    private JdbcLocationRepository repo;

   /* @BeforeEach
    public void setUp() {
        Mockito.lenient().when(repo.getBuilding(any))
    }*/

    @Test
    public void getBuildingReturnsNullIfInvalidId(){

    }
}
