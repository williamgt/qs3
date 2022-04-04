package no.ntnu.idatt2105.gr13.qs3backend.service;

import no.ntnu.idatt2105.gr13.qs3backend.repository.location.JdbcLocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;

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
