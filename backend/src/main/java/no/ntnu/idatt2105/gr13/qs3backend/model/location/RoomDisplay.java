package no.ntnu.idatt2105.gr13.qs3backend.model.location;

/**
 * Model for displaying a room
 */
public class RoomDisplay {
    private int roomId;
    private int tableAmount;
    private String roomName;

    /**
     * Constructor for RoomDisplay
     * Needs id, number of tables and room name
     * @param roomId
     * @param tableAmount
     * @param roomName
     */
    public RoomDisplay(int roomId, int tableAmount, String roomName) {
        if(roomId < 0){
            throw new IllegalArgumentException("RoomId can't be less than 0");
        }
        if(tableAmount < 0){
            throw new IllegalArgumentException("TableAmount can't be less than 0");
        }
        if(roomName.isEmpty()){
            throw new IllegalArgumentException("RoomName can't be empty");
        }
        this.roomId = roomId;
        this.tableAmount = tableAmount;
        this.roomName = roomName;
    }

    /**
     * Getter for id
     * @return
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * Getter for number of tables
     * @return
     */
    public int getTableAmount() {
        return tableAmount;
    }

    /**
     * Getter for room name
     * @return
     */
    public String getRoomName() {
        return roomName;
    }
}
