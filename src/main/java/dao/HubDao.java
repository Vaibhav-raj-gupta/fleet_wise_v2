package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Hub;
import model.Route;
import utils.DBConnection;

public class HubDao implements IDao<Hub> {
    
    private DBConnection dbConnection;

    public HubDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // SQL queries
    private static final String CREATE_QUERY = "INSERT INTO hubs (route_id, hub_Name, address, city, pincode, contact_Number, email_Address) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE hubs SET route_Id=?, hub_Name=?, address=?, city=?, pincode=?, contact_Number=?, email_Address=? WHERE hub_Id = ?";
    private static final String DELETE_QUERY = "DELETE FROM hubs WHERE hub_Id = ?";
    private static final String FIND_ONE_QUERY = "SELECT * FROM hubs WHERE hub_Id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM hubs";
    private static final String GET_HUBS_BY_ROUTE_ID_QUERY = "SELECT * FROM hubs WHERE route_Id = ?";

    @Override
    public Hub create(Hub hub) throws SQLException {
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(CREATE_QUERY)) {
            stmt.setInt(1, hub.getRoute());
            stmt.setString(2, hub.getHubName());
            stmt.setString(3, hub.getAddress());
            stmt.setString(4, hub.getCity());
            stmt.setInt(5, hub.getPincode());
            stmt.setLong(6, hub.getContactNumber());
            stmt.setString(7, hub.getEmailAddress());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return hub;
    }

    @Override
    public boolean update(int id, Hub hub) throws SQLException {
        boolean updated = false;
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
            stmt.setInt(1, hub.getRoute());
            stmt.setString(2, hub.getHubName());
            stmt.setString(3, hub.getAddress());
            stmt.setString(4, hub.getCity());
            stmt.setInt(5, hub.getPincode());
            stmt.setLong(6, hub.getContactNumber());
            stmt.setString(7, hub.getEmailAddress());
            stmt.setInt(8, id);
            int rowsAffected = stmt.executeUpdate();
            updated = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return updated;
    }
    
    
    @Override
    public boolean delete(int id) throws SQLException {
        boolean deleted = false;
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            deleted = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return deleted;
    }

    @Override
    public Hub findOne(int id) throws SQLException {
        Hub hub = null;
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_ONE_QUERY)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    hub = extractHubFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return hub;
    }

    @Override
    public List<Hub> findAll() throws SQLException {
        List<Hub> hubs = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_ALL_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Hub hub = extractHubFromResultSet(rs);
                hubs.add(hub);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return hubs;
    }
    
    public List<Hub> getHubsByRouteId(int routeId) {
        List<Hub> hubs = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_HUBS_BY_ROUTE_ID_QUERY)) {
            stmt.setInt(1, routeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Hub hub = extractHubFromResultSet(rs);
                    hubs.add(hub);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hubs;
    }

    public boolean assignRouteToHub(int routeId, Hub hub) throws SQLException {
    	boolean updated = false;
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE hubs SET routeId=?, hubName=?, address=?, city=?, pincode=?, contactNumber=?, emailAddress=? WHERE hubId = ?")) {
            stmt.setInt(1, routeId);
            stmt.setString(2, hub.getHubName());
            stmt.setString(3, hub.getAddress());
            stmt.setString(4, hub.getCity());
            stmt.setInt(5, hub.getPincode());
            stmt.setLong(6, hub.getContactNumber());
            stmt.setString(7, hub.getEmailAddress());
            stmt.setInt(8, hub.getHubId());
            int rowsAffected = stmt.executeUpdate();
            updated = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return updated;
    }
    
    private Hub extractHubFromResultSet(ResultSet rs) throws SQLException {
        Hub hub = new Hub();
        hub.setHubId(rs.getInt("hubId"));
        RouteDao routeDao = new RouteDao(dbConnection);
        Route route = routeDao.findOne(rs.getInt("route"));
        hub.setRoute(rs.getInt("route"));
        hub.setHubName(rs.getString("hubName"));
        hub.setAddress(rs.getString("address"));
        hub.setCity(rs.getString("city"));
        hub.setPincode(rs.getInt("pincode"));
        hub.setContactNumber(rs.getLong("contactNumber"));
        hub.setEmailAddress(rs.getString("emailAddress"));
        return hub;
    }

}
