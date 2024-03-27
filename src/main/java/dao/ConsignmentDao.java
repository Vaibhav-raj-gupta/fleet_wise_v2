package dao;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Consignment;
import model.Hub;
import model.Trip;
import utils.DBConnection;

public class ConsignmentDao implements IDao<Consignment> {

    private DBConnection dbConnection;

    public ConsignmentDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Consignment create(Consignment consignment) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dbConnection.getConnection();
            String query = "INSERT INTO consignments (hub_Id, consignment_Date, consignment_Name, consignment_Address, status) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, consignment.getHub().getHubId());
            statement.setString(2, consignment.getConsignmentDate());
            statement.setString(3, consignment.getConsignmentName());
            statement.setString(4, consignment.getConsignmentAddress());
            statement.setString(5, consignment.getStatus());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating consignment failed, no rows affected.");
            }
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                consignment.setConsignmentId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating consignment failed, no ID obtained.");
            }
        } finally {
            if (generatedKeys != null) {
                generatedKeys.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return consignment;
    }

    @Override
    public boolean update(int id, Consignment consignment) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null; 
        try {
            connection = dbConnection.getConnection();
            String query = "UPDATE consignments SET hub_Id=?, trip_Id=?, consignment_Date=?, consignment_Name=?, consignment_Address=?, status=? WHERE consignmentId=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, consignment.getHub().getHubId());
            statement.setInt(2, consignment.getTrip().getTripId());
            statement.setString(3, consignment.getConsignmentDate());
            statement.setString(4, consignment.getConsignmentName());
            statement.setString(5, consignment.getConsignmentAddress());
            statement.setString(6, consignment.getStatus());
            statement.setInt(7, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.getConnection();
            String query = "DELETE FROM consignments WHERE consignment_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Consignment findOne(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Consignment consignment = null;
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM consignments WHERE consignment_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                consignment = extractConsignmentFromResultSet(resultSet);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return consignment;
    }

    @Override
    public List<Consignment> findAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Consignment> consignments = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM consignments";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Consignment consignment = extractConsignmentFromResultSet(resultSet);
                consignments.add(consignment);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return consignments;
    }
    
    public List<Consignment> getConsignmentsByTripId(int tripId) throws SQLException {
        List<Consignment> consignments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM consignments WHERE trip_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, tripId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Consignment consignment = extractConsignmentFromResultSet(resultSet);
                consignments.add(consignment);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return consignments;
    }
    
    public List<Consignment> getConsignmentsByHubId(int hubId) throws SQLException {
        List<Consignment> consignments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM consignments WHERE hub_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, hubId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Consignment consignment = extractConsignmentFromResultSet(resultSet);
                consignments.add(consignment);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return consignments;
    }
    
    public List<Consignment> getConsignmentsByRouteId(int routeId) throws SQLException {
        List<Consignment> consignments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT c.Consignment_ID, c.Trip_ID, c.hub_id, c.Consignment_Date, c.Consignment_name, c.Consignment_address, c.Status FROM consignments c inner join hubs h on h.hub_id = c.hub_id  WHERE h.route_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, routeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Consignment consignment = extractConsignmentFromResultSet(resultSet);
                consignments.add(consignment);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return consignments;
    }
    


    private Consignment extractConsignmentFromResultSet(ResultSet resultSet) throws SQLException {
        Consignment consignment = new Consignment();
        consignment.setConsignmentId(resultSet.getInt("consignment_Id"));
        HubDao hubDao = new HubDao(dbConnection);
        Hub hub = hubDao.findOne(resultSet.getInt("hub_Id"));
        consignment.setHub(hub);
        TripDao tripDao = new TripDao(dbConnection);
        Trip trip = tripDao.findOne(resultSet.getInt("trip_Id"));
        consignment.setTrip(trip);
        Date consignmentDate = resultSet.getDate("consignment_Date");
        consignment.setConsignmentDate(consignmentDate.toString()); 
        consignment.setConsignmentName(resultSet.getString("consignment_Name"));
        consignment.setConsignmentAddress(resultSet.getString("consignment_Address"));
        consignment.setStatus(resultSet.getString("status"));
        return consignment;
    }
}
