package no.ntnu.idatt2105.gr13.qs3backend.repository.location;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Building;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.Campus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.RoomDisplay;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.single.SimpleCampus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcLocationRepository implements LocationRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Campus> getLocations() {

        List<Campus> campuses = jdbcTemplate.query("SELECT * from Campus", (rs, rowNum) ->
                new Campus(
                        rs.getInt("campusId"),
                        rs.getString("campusName")
                ));

        List<Building> buildings = new ArrayList<>();
        List<RoomDisplay> roomDisplays = new ArrayList<>();
        for (Campus campus : campuses) {
            campus.addBuildings(jdbcTemplate.query("SELECT * from Campus, Building where" +
                    " Campus.campusId = Building.campusId and Campus.campusId = ?", (rs, rowNum) ->
                    new Building(
                            rs.getInt("buildingId"),
                            rs.getString("buildingName")
                    ), campus.getId()));
            for(Building building : campus.getBuildings()){

                System.out.println(building.getId());
                roomDisplays =  jdbcTemplate.query("SELECT * from Building, Room where" +
                        " Room.buildingId=? and Room.buildingId = Building.buildingId", (rs, rowNum) ->
                        new RoomDisplay(
                                rs.getInt("roomId"),
                                rs.getInt("tables"),
                                rs.getString("roomName")
                        ), building.getId());

                building.addRooms(roomDisplays);

            }
            campus.addBuildings(buildings);
        }

        return campuses;
    }

    @Override
    public List<SimpleCampus> getCampuses() {

        List<SimpleCampus> campuses = jdbcTemplate.query("SELECT * from Campus", (rs, rowNum) ->
                new SimpleCampus(
                        rs.getInt("campusId"),
                        rs.getString("campusName")
                ));

        return campuses;
    }

    @Override
    public SimpleCampus getCampus(int id) {

        SimpleCampus campus = jdbcTemplate.queryForObject("SELECT * from Campus where campusId=?", (rs, rowNum) ->
                new SimpleCampus(
                        rs.getInt("campusId"),
                        rs.getString("campusName")
                ), id);

        return campus;
    }
}
