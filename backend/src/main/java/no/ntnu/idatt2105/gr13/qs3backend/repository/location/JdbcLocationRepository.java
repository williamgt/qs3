package no.ntnu.idatt2105.gr13.qs3backend.repository.location;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Building;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.Campus;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.register.RegisterRoom;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.RoomDisplay;
import no.ntnu.idatt2105.gr13.qs3backend.model.location.simple.*;
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
                        rs.getString("campusName"),
                        rs.getInt("campusId")
                ));

        return campuses;
    }

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
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean registerCampus(String name) {
        try {
            SimpleCampus campus = getCampus(name);
            return false;
        }catch (Exception e){
            try{
                int rows = jdbcTemplate.update("INSERT INTO Campus(campusName) values (?)", name);
            }catch (Exception er){
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean registerRoom(RegisterRoom room) {
        try {
            getRoom(room.getRoomName(), room.getBuildingId());
            return false;
        }catch (Exception e){
            try{
                String sql = "INSERT INTO Room(buildingId, roomName, tables, floor) VALUES (?,?,?,?)";
                Object[] args = {room.getBuildingId(), room.getRoomName(), room.getTables(), room.getFloors()};
                int rows = jdbcTemplate.update(sql, args);
                return true;
            }catch (Exception er){
                return false;
            }
        }
    }

    @Override
    public Boolean registerBuilding(SimpleBuilding building) {
        try {
            getBuilding(building.getName(), building.getId());
            return false;
        }catch (Exception e){
            try{
                String sql = "INSERT INTO Building(campusId, buildingName) VALUES (?,?)";
                Object[] args = {building.getId(), building.getName()};
                int rows = jdbcTemplate.update(sql, args);
                return true;
            }catch (Exception er){
                return false;
            }
        }
    }

    @Override
    public int editCampus(SimpleCampus campus) {
        try{
            getCampus(campus.getName());
            return -1;
        }catch (Exception e){
            try{
                String sql = "UPDATE Campus set Campus.campusName = ? where campusId = ?";
                Object[] args = {campus.getName(), campus.getId()};
                int rows = jdbcTemplate.update(sql, args);
                return rows;
            }catch (Exception er){
                return -1;
            }
        }
    }

    @Override
    public int editBuilding(SimpleBuilding building) {
        try{
            getBuilding(building.getName(), building.getId());
            return -1;
        }catch (Exception e){
            try{
                String sql = "UPDATE Building set Building.buildingName = ? where buildingId = ?";
                Object[] args = {building.getName(), building.getId()};
                int rows = jdbcTemplate.update(sql, args);
                return rows;
            }catch (Exception er){
                return -1;
            }
        }
    }

    @Override
    public int editRoom(SimpleRoom room) {
        try{
            getRoom(room.getRoomName(), room.getId());
            return -1;
        }catch (Exception e){
            try{
                String sql = "UPDATE Room set Room.roomName = ?, Room.tables = ?, Room.floor = ? where roomId = ?";
                Object[] args = {room.getRoomName(), room.getTables(), room.getFloors(), room.getId()};
                int rows = jdbcTemplate.update(sql, args);
                return rows;
            }catch (Exception er){
                return -1;
            }
        }
    }

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

    private SimpleCampus getCampus(String name){
        SimpleCampus campusCheck = jdbcTemplate.queryForObject("SELECT * from Campus where campusName=?", (rs, rowNum) ->
                new SimpleCampus(
                        rs.getString("campusName"),
                        rs.getInt("campusId")
                ), name);
        return campusCheck;
    }

    private SimpleBuilding getBuilding(String name, int campusId){
        Object[] args1 = {name, campusId};
        SimpleBuilding buildingCheck = jdbcTemplate.queryForObject("SELECT * from Building where buildingName=? and campusId=?", (rs, rowNum) ->
                new SimpleBuilding(
                        rs.getString("buildingName"),
                        rs.getInt("buildingId")
                ), args1);
        return buildingCheck;
    }

    private RegisterRoom getRoom(String roomName, int buildingId){
        Object[] args1 = {roomName, buildingId};
        RegisterRoom roomCheck = jdbcTemplate.queryForObject("SELECT * from Room where roomName=? and buildingId=?", (rs, rowNum) ->
                new RegisterRoom(
                        rs.getInt("buildingId"),
                        rs.getString("roomName"),
                        rs.getInt("floor"),
                        rs.getInt("tables")
                ), args1);
        return roomCheck;
    }
}
