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
    public boolean update(int hubId, Hub updatedHub) throws SQLException {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Define the SQL query for the update operation
        	connection = dbConnection.getConnection();
            String sqlQuery = "UPDATE Hubs SET Route_ID=?, Hub_Name=?, Address=?, City=?, Pincode=?, Contact_Number=?, Email_Address=? WHERE Hub_ID=?";
            
            // Create a PreparedStatement
            statement = connection.prepareStatement(sqlQuery);
            
            // Set parameters for the PreparedStatement
            statement.setInt(1, updatedHub.getRoute());
            statement.setString(2, updatedHub.getHubName());
            statement.setString(3, updatedHub.getAddress());
            statement.setString(4, updatedHub.getCity());
            statement.setInt(5, updatedHub.getPincode());
            statement.setLong(6, updatedHub.getContactNumber());
            statement.setString(7, updatedHub.getEmailAddress());
            statement.setInt(8, hubId); // Set the Hub_ID parameter
            
            // Execute the update operation
            int rowsUpdated = statement.executeUpdate();
            
            // Check if the update was successful
            if (rowsUpdated > 0) {
                success = true;
            }
        }  catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
        return success;
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
    public Hub findOne(int hubId) throws SQLException {
    	
    	Connection connection = dbConnection.getConnection();
        String sqlQuery = "SELECT * FROM Hubs WHERE Hub_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, hubId);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        Hub hub = null;
        
        if (resultSet.next()) {
            int HubId = resultSet.getInt("Hub_ID");
            int RouteID = resultSet.getInt("Route_ID");
            String HubName = resultSet.getString("Hub_Name");
            String Address = resultSet.getString("Address");
            String City = resultSet.getString("City");
            int pincode = resultSet.getInt("Pincode");
            long contactNumber = resultSet.getLong("Contact_Number");
            String Email = resultSet.getString("Email_Address");
            
            hub = new Hub(HubId,RouteID,HubName,Address,City,pincode,contactNumber,Email);
        }
        

        return hub;
    }

    @Override
    public List<Hub> findAll() throws SQLException {
        List<Hub> hubs = new ArrayList<>();

        Connection connection = dbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Hubs");
        ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int hubId = resultSet.getInt("Hub_ID");
                int routeId = resultSet.getInt("Route_ID");
                String hubName = resultSet.getString("Hub_Name");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                int pincode = resultSet.getInt("Pincode");
                long contactNumber = resultSet.getLong("Contact_Number");
                String emailAddress = resultSet.getString("Email_Address");

                Hub hub = new Hub(hubId, routeId, hubName, address, city, pincode, contactNumber, emailAddress);
                hubs.add(hub);
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
