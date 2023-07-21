
package com.mio.DAL;

import com.mio.DTO.Room;
import com.mio.DTO.SO;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomAccess {
    private static RoomAccess instance;
    public static RoomAccess getInstance() {
        if(instance == null)
            instance = new RoomAccess();
        return instance;
    }    
    private RoomAccess() {}

    public List<Room> getRoomList() throws SQLException, IOException {
        String query = "select id, name, size, is_lab from room";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        List<Room> list = new ArrayList<>();
        while(r.next()) {
            list.add(new Room(r.getInt(1), r.getString(2), r.getInt(3), r.getBoolean(4)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public List<SO> getSRoomList() throws SQLException, IOException {
        String query = "select id, name from room";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet r = p.executeQuery();
        List<SO> list = new ArrayList<>();
        while(r.next()) {
            list.add(new SO(r.getInt(1), r.getString(2)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public List<SO> getSRoomLabList() throws SQLException, IOException {
        String query = "select id, name from room where is_lab=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setBoolean(1, true);
        ResultSet r = p.executeQuery();
        List<SO> list = new ArrayList<>();
        while(r.next()) {
            list.add(new SO(r.getInt(1), r.getString(2)));
        }
        p.close();
        r.close();
        return list;
    }
    public List<SO> getSRoomNormalList() throws SQLException, IOException {
        String query = "select id, name from room where is_lab=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setBoolean(1, false);
        ResultSet r = p.executeQuery();
        List<SO> list = new ArrayList<>();
        while(r.next()) {
            list.add(new SO(r.getInt(1), r.getString(2)));
        }
        p.close();
        r.close();
        return list;
    }
    
    public void addRoom(Room s) throws SQLException, IOException {
        String query = "insert into room(name, size, is_lab) values(?,?,?)";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, s.getName());
        p.setInt(2, s.getSize());
        p.setBoolean(3, s.isLab());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        if(r.next()) {
            s.setId(r.getInt(1));
        }
        r.close();
        p.close();
    }
    
    public boolean updateRoom(Room s) throws SQLException, IOException {
        String query = "update room set name=?,size=?,is_lab=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, s.getName());
        p.setInt(2, s.getSize());
        p.setBoolean(3, s.isLab());
        p.setInt(4, s.getId());
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public boolean delRoom(int id) throws SQLException, IOException {
        String query = "delete from room where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
    public Room getRoom(int id) throws SQLException, IOException {
        String query = "select name, size, is_lab from room where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        Room room;
        room = null;
        if(r.next()) {
            room = new Room(id, r.getString(1), r.getInt(2), r.getBoolean(3));
        }
        r.close();
        p.close();
        return room;
    }
    
    public SO getSRoom(int id) throws SQLException, IOException {
        String query = "select name from room where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        SO sr;
        sr = null;
        if(r.next()) {
            sr = new SO(id, r.getString(1));
        }
        r.close();
        p.close();
        return sr;
    }
    public String getBusy(int id) throws SQLException, IOException {
        String query = "select busy from room where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        String busy = null;
        if(r.next()) {
            busy = r.getString(1);
        }
        r.close();
        p.close();
        return busy;
    }
    public boolean updateBusy(int id, String busy) throws SQLException, IOException {
        String query = "update room set busy=? where id=?";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        p.setString(1, busy);
        p.setInt(2, id);
        int rtn = p.executeUpdate();
        p.close();
        return rtn == 1;
    }
}
