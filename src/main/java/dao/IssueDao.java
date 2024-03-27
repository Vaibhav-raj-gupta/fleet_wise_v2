package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Issue;
import model.Route;
import utils.DBConnection;

public class IssueDao implements IDao<Issue> {
    
    private DBConnection dbConnection;

    public IssueDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // SQL queries
    private static final String CREATE_QUERY = "INSERT INTO Issues (Consignment_id,driver_id,Remarks,Description) VALUES (?, ?, ?, ?)";
    private static final String CREATE_QUERY1 = "INSERT INTO Issues (vehicle_id,driver_id,Remarks,Description) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Issues SET Vehicle_id=?, Consignment_id=?, Remarks=?, Description=? WHERE Issue_Id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Issues WHERE Issue_Id = ?";
    private static final String FIND_ONE_QUERY = "SELECT * FROM Issues WHERE Issue_Id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM Issues";
    private static final String GET_IssueS_BY_ROUTE_ID_QUERY = "SELECT * FROM Issues WHERE Issue_Id = ?";

    @Override
    public Issue create(Issue Issue) throws SQLException {
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(CREATE_QUERY)) {
            stmt.setInt(1, Issue.getConsignmentId());
            stmt.setInt(2, Issue.getDriverId());
            stmt.setString(3, Issue.getRemarks());
            stmt.setString(4, Issue.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return Issue;
    }
    
    public Issue create1(Issue Issue) throws SQLException {
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(CREATE_QUERY1)) {
            stmt.setInt(1, Issue.getVehicleId());
            stmt.setInt(2, Issue.getDriverId());
            stmt.setString(3, Issue.getRemarks());
            stmt.setString(4, Issue.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return Issue;
    }
    
    @Override
    public boolean update(int id, Issue Issue) throws SQLException {
        boolean updated = false;
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
            stmt.setInt(1, Issue.getRoute());
            stmt.setString(2, Issue.getIssueName());
            stmt.setString(3, Issue.getAddress());
            stmt.setString(4, Issue.getCity());
            stmt.setInt(5, Issue.getPincode());
            stmt.setLong(6, Issue.getContactNumber());
            stmt.setString(7, Issue.getEmailAddress());
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
    public Issue findOne(int id) throws SQLException {
        Issue Issue = null;
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_ONE_QUERY)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Issue = extractIssueFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return Issue;
    }

    @Override
    public List<Issue> findAll() throws SQLException {
        List<Issue> Issues = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_ALL_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Issue Issue = extractIssueFromResultSet(rs);
                Issues.add(Issue);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return Issues;
    }
    
    public List<Issue> getIssuesByRouteId(int routeId) {
        List<Issue> Issues = new ArrayList<>();
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_IssueS_BY_ROUTE_ID_QUERY)) {
            stmt.setInt(1, routeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Issue Issue = extractIssueFromResultSet(rs);
                    Issues.add(Issue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Issues;
    }

    public boolean assignRouteToIssue(int routeId, Issue Issue) throws SQLException {
    	boolean updated = false;
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE Issues SET routeId=?, IssueName=?, address=?, city=?, pincode=?, contactNumber=?, emailAddress=? WHERE IssueId = ?")) {
            stmt.setInt(1, routeId);
            stmt.setString(2, Issue.getIssueName());
            stmt.setString(3, Issue.getAddress());
            stmt.setString(4, Issue.getCity());
            stmt.setInt(5, Issue.getPincode());
            stmt.setLong(6, Issue.getContactNumber());
            stmt.setString(7, Issue.getEmailAddress());
            stmt.setInt(8, Issue.getIssueId());
            int rowsAffected = stmt.executeUpdate();
            updated = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return updated;
    }
    
    private Issue extractIssueFromResultSet(ResultSet rs) throws SQLException {
        Issue Issue = new Issue();
        Issue.setIssueId(rs.getInt("IssueId"));
        RouteDao routeDao = new RouteDao(dbConnection);
        Route route = routeDao.findOne(rs.getInt("route"));
        Issue.setRoute(rs.getInt("route"));
        Issue.setIssueName(rs.getString("IssueName"));
        Issue.setAddress(rs.getString("address"));
        Issue.setCity(rs.getString("city"));
        Issue.setPincode(rs.getInt("pincode"));
        Issue.setContactNumber(rs.getLong("contactNumber"));
        Issue.setEmailAddress(rs.getString("emailAddress"));
        return Issue;
    }

}
