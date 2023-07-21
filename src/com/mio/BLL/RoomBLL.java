
package com.mio.BLL;

import com.mio.DAL.RoomAccess;
import com.mio.DTO.Room;
import com.mio.DTO.SO;
import java.util.ArrayList;
import java.util.List;

public class RoomBLL {
    private static RoomBLL instance;
    public static RoomBLL getInstance() {
        if(instance == null)
            instance = new RoomBLL();
        return instance;
    }    
    private RoomBLL() {}

    public void addRoom(Room s) {
        try {
            RoomAccess.getInstance().addRoom(s);
        } catch(Exception e) {
            System.err.println(e);
        }
    }
    public Room getRoom(int id) {
        try {
            return RoomAccess.getInstance().getRoom(id);
        } catch(Exception e) {
            return null;
        }
    }
    public boolean updateRoom(Room s) {
        try {
            return RoomAccess.getInstance().updateRoom(s);
        } catch(Exception e) {
            return false;
        }
    }
    public boolean delRoom(int id) {
        try {
            return RoomAccess.getInstance().delRoom(id);
        } catch(Exception e) {
            return false;
        }
    }
    public List<Room> getRoomList() {
        try {
            return RoomAccess.getInstance().getRoomList();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public List<SO> getSRoomList() {
        try {
            return RoomAccess.getInstance().getSRoomList();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    public List<SO> getSRoomNormalList() {
        try {
            return RoomAccess.getInstance().getSRoomNormalList();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    public List<SO> getSRoomLabList() {
        try {
            return RoomAccess.getInstance().getSRoomLabList();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }
    public SO getSRoom(int roomId) {
        try {
            return RoomAccess.getInstance().getSRoom(roomId);
        } catch(Exception e) {
            return null;
        }
    }
    public String getBusy(int id) {
        try {
            return RoomAccess.getInstance().getBusy(id);
        } catch(Exception e) {
            return null;
        }
    }
    public boolean updateBusy(int id, String busy) {
        try {
            return RoomAccess.getInstance().updateBusy(id, busy);
        } catch(Exception e) {
            return false;
        }
    }
}
