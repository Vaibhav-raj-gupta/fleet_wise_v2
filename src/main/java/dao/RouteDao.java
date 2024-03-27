
package dao;

import model.Route;
import model.Hub;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RouteDao implements IDao<Route> {

    private DBConnection dbConnection;

    public RouteDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Route create(Route route) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dbConnection.getConnection();
            String query = "INSERT INTO routes(route_Name, start_Point, destination_Point) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, route.getRouteName());
            statement.setString(2, route.getStartPoint());
            statement.setString(3, route.getDestinationPoint());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating route failed, no rows affected.");
            }
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                route.setRouteId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating route failed, no ID obtained.");
            }
        }  catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return route;
    }

    @Override
    public boolean update(int id, Route route) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.getConnection();
            String query = "UPDATE routes SET route_Name=?, start_Point=?, destination_Point=? WHERE route_Id=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, route.getRouteName());
            statement.setString(2, route.getStartPoint());
            statement.setString(3, route.getDestinationPoint());
            statement.setInt(4, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }  catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.getConnection();
            String query = "DELETE FROM routes WHERE routeId=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }  catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Route findOne(int routeId) throws SQLException {
    	
    	Connection connection = dbConnection.getConnection();
        String sqlQuery = "SELECT * FROM Routes WHERE Route_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, routeId);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        Route route = null;

            if (resultSet.next()) {
                String routeName = resultSet.getString("Route_Name");
                String startPoint = resultSet.getString("Start_Point");
                String destinationPoint = resultSet.getString("Destination_Point");

                route = new Route(routeId, routeName, startPoint, destinationPoint);
            }
        

        return route;
    }

    @Override
    public List<Route> findAll() throws SQLException {
        List<Route> routes = new ArrayList<>();

        Connection connection = dbConnection.getConnection();
        Statement selectStatement = connection.createStatement();

            final String sqlQuery = "SELECT * FROM Routes";
            ResultSet resultSet = selectStatement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int routeId = resultSet.getInt("Route_ID");
                String routeName = resultSet.getString("Route_Name");
                String startPoint = resultSet.getString("Start_Point");
                String destinationPoint = resultSet.getString("Destination_Point");

                Route route = new Route(routeId, routeName, startPoint, destinationPoint);
                routes.add(route);
            }

        return routes;
    }
    

    private Route extractRouteFromResultSet(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setRouteId(resultSet.getInt("routeId"));
        route.setRouteName(resultSet.getString("routeName"));
        route.setStartPoint(resultSet.getString("startPoint"));
        route.setDestinationPoint(resultSet.getString("destinationPoint"));
        return route;
    }
    
    public List<Route> findRoutesByTripId(int tripId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Route> routes = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT r.* " +
                           "FROM routes r " +
                           "INNER JOIN trips t ON r.route_id = t.route_id " +
                           "WHERE t.trip_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, tripId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Route route = extractRouteFromResultSet(resultSet);
                routes.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return routes;
    }


}
