package no.ntnu.idatt2105.gr13.qs3backend.model.location.simple;

/**
 * Model for a simple room
 */
public class SimpleRoom {
    private int id;
    private int tables;
    private String roomName;
    private int floors;

    /**
     * Constructor. Needs room id, number of tables, room name and floor
     * @param id
     * @param tables
     * @param roomName
     * @param floors
     */
    public SimpleRoom(int id, int tables, String roomName, int floors) {
        if(id < 0){
            throw new IllegalArgumentException("Id can't be less than 0");
        }
        if(tables < 0){
            throw new IllegalArgumentException("Number of tables can't be less than 0");
        }
        if(roomName.isEmpty()) {
            throw new IllegalArgumentException("Room name can't be empty");
        }
        this.id = id;
        this.tables = tables;
        this.roomName = roomName;
        this.floors = floors;
    }

    /**
     * Getter for id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for number of tables
     * @return
     */
    public int getTables() {
        return tables;
    }

    /**
     * Getter for room name
     * @return
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Getter for floor
     * @return
     */
    public int getFloors() {
        return floors;
    }
}
