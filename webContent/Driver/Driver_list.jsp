<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Driver" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Driver List</title>
</head>
<body>
	<button><a href="DriverServlet">refresh</a></button>
    <h1>Driver List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>License Number</th>
            <!-- Add more columns as needed -->
        </tr>
        <% 
         List<Driver> drivers = (List<Driver>) request.getAttribute("drivers");
            if (drivers != null && !drivers.isEmpty()) {
                for (Driver driver : drivers) {
        %>
        <tr>
            <td><a href="UpdateDriverServlet?driver_Id=<%= driver.getDriverId() %>"><%= driver.getDriverId() %></a></td>
            <td><%= driver.getName() %></td>
            <td><%= driver.getLicenceNo() %></td>
            <!-- Display other driver details here -->
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="3">No drivers found</td>
        </tr>
        <% } %>
    </table>
</body>
</html>
