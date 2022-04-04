package no.ntnu.idatt2105.gr13.qs3backend.repository.location;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Building;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.Campus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.register.RegisterRoom;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.RoomDisplay;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.*;
import no.ntnu.idatt2105.gr13.qs3backend.repository.course.JdbcCourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository used to communicate with the DB regarding locations
 */
@Repository
public class JdbcLocationRepository implements LocationRepository{

    Logger logger = LoggerFactory.getLogger(JdbcLocationRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Gets a list of campuses that each have a list of buildings that each have a list of rooms.
     * @return list of campuses
     */
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

    /**
     * Gets a list of only simple campuses.
     * @return
     */
    @Override
    public List<SimpleCampus> getCampuses() {

        List<SimpleCampus> campuses = jdbcTemplate.query("SELECT * from Campus", (rs, rowNum) ->
                new SimpleCampus(
                        rs.getString("campusName"),
                        rs.getInt("campusId")
                ));

        return campuses;
    }

    /**
     * Gets a list of simple buildings related to a campus ID.
     * @param id the id
     * @return
     */
    @Override
    public SimpleCampusBuilding getCampus(int id) {

        SimpleCampusBuilding campus = jdbcTemplate.queryForObject("SELECT * from Campus where campusId=?", (rs, rowNum) ->
                new SimpleCampusBuilding(
                        rs.getInt("campusId"),
                        rs.getString("campusName")
                ), id);
        List<SimpleBuilding> buildings = (jdbcTemplate.query("SELECT * from Campus, Building where" +
                " Campus.campusId = Building.campusId and Campus.campusId = ?", (rs, rowNum) ->
                new SimpleBuilding(
                        rs.getString("buildingName"),
                        rs.getInt("buildingId")
                ), campus.getId()));

        campus.addBuildings((ArrayList<SimpleBuilding>) buildings);
        return campus;
    }

    /**
     * Gets a campus with the related building and rooms given a building ID.
     * Will throw an exception if none or more than one building is found with the given ID.
     * @param id the id
     * @return campus with related building and rooms with given id
     */
    @Override
    public SimpleCampusBuildingRoom getBuilding(int id) {
        try{
            List<SimpleRoom> room = jdbcTemplate.query("SELECT * from Building, Room where" +
                    " Room.buildingId=? and Room.buildingId = Building.buildingId", (rs, rowNum) ->
                    new SimpleRoom(
                            rs.getInt("roomId"),
                            rs.getInt("tables"),
                            rs.getString("roomName"),
                            rs.getInt("floor")
                    ), id);

            SimpleBuildingRoom building = jdbcTemplate.queryForObject("SELECT * from Building where" +
                    " Building.buildingId=?", (rs, rowNum) ->
                    new SimpleBuildingRoom(
                            rs.getInt("buildingId"),
                            rs.getString("buildingName")
                    ), id);

            building.addRooms((ArrayList<SimpleRoom>) room);

            SimpleCampusBuildingRoom campus = jdbcTemplate.queryForObject("SELECT * from Campus, Building where" +
                    " Campus.campusId = Building.campusId and Building.buildingId= ?", (rs, rowNum) ->
                    new SimpleCampusBuildingRoom(
                            rs.getInt("campusId"),
                            rs.getString("campusName"),
                            building
                    ), id);

            return campus;
        }catch (Exception e){
            logger.info("Something went wrong when getting campus: " + e.getMessage());
        }
        return null;
    }

    /**
     * Registers a new campus.
     * @param name the name of the campus
     * @return true
     */
    @Override
    public Boolean registerCampus(String name) {
        int rows = jdbcTemplate.update("INSERT INTO Campus(campusName) values (?)", name);
        return true;
    }

    /**
     * Registers a new room.
     * @param room the room
     * @return true if registration successful, false if not
     */
    @Override
    public Boolean registerRoom(RegisterRoom room) {
        try {
            String sql = "INSERT INTO Room(buildingId, roomName, tables, floor) VALUES (?,?,?,?)";
            Object[] args = {room.getBuildingId(), room.getRoomName(), room.getTables(), room.getFloors()};
            int rows = jdbcTemplate.update(sql, args);

            return true;
        }catch (Exception e){
            logger.info("Something went wrong when registering a room: " + e.getMessage());
            return false;
        }
    }

    /**
     * Registers a new building.
     * @param building the building
     * @return rue if registration successful, false if not
     */
    @Override
    public Boolean registerBuilding(SimpleBuilding building) {
        try {
            String sql = "INSERT INTO Building(campusId, buildingName) VALUES (?,?)";
            Object[] args = {building.getId(), building.getName()};
            int rows = jdbcTemplate.update(sql, args);

            return true;
        }catch (Exception e){
            logger.info("Something went wrong when registering a building: " + e.getMessage());
            return false;
        }
    }

    /**
     * Edits the name of a campus.
     * @param campus the campus to be edited including the new name
     * @return the amount of affected rows of -1 if an exception is thrown
     */
    @Override
    public int editCampus(SimpleCampus campus) {
        try{
            String sql = "UPDATE Campus set Campus.campusName = ? where campusId = ?";
            Object[] args = {campus.getName(), campus.getId()};
            int rows = jdbcTemplate.update(sql, args);
            return rows;
        }catch (Exception e){
            logger.info("Something went wrong when editing a campus: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Edits the name of a building.
     * @param building the building to be edited including the new name
     * @return the amount of affected rows of -1 if an exception is thrown
     */
    @Override
    public int editBuilding(SimpleBuilding building) {
        try{
            String sql = "UPDATE Building set Building.buildingName = ? where buildingId = ?";
            Object[] args = {building.getName(), building.getId()};
            int rows = jdbcTemplate.update(sql, args);
            return rows;
        }catch (Exception e){
            logger.info("Something went wrong when editing a building: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Edits the name, tables and floor of a room.
     * @param room the room to be edited including the new name, tables and floor
     * @return the amount of affected rows of -1 if an exception is thrown
     */
    @Override
    public int editRoom(SimpleRoom room) {
        try{
            String sql = "UPDATE Room set Room.roomName = ?, Room.tables = ?, Room.floor = ? where roomId = ?";
            Object[] args = {room.getRoomName(), room.getTables(), room.getFloors(), room.getId()};
            int rows = jdbcTemplate.update(sql, args);
            return rows;
        }catch (Exception e){
            logger.info("Something went wrong when editing a room: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Gets a room given a room ID.
     * @param id the id of the room in question
     * @return
     */
    @Override
    public SimpleRoomWBC getRoom(int id) {
        SimpleRoomWBC room = jdbcTemplate.queryForObject("SELECT * from Room, Building, Campus where" +
                " Room.buildingId = Building.buildingId and Building.campusId = Campus.campusId and Room.roomId = ?", (rs, rowNum) ->
                new SimpleRoomWBC(
                        rs.getInt("roomId"),
                        rs.getInt("tables"),
                        rs.getString("roomName"),
                        rs.getInt("floor"),
                        rs.getString("campusName"),
                        rs.getString("buildingName")
                ), id);
        return room;
    }
}
