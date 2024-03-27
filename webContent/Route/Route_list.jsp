<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Route" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Driver List</title>
</head>
<body>
	<button><a href="RouteServlet">refresh</a></button>
    <h1>Route List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <!-- Add more columns as needed -->
        </tr>
        <% 
         List<Route> Route = (List<Route>) request.getAttribute("Routes");
            if (Route != null && !Route.isEmpty()) {
                for (Route route : Route) {
        %>
        <tr>
            <td><a href="UpdateRouteServlet?Route_Id=<%= route.getRouteId() %>"><%= route.getRouteId() %></a></td>
            <td><%= route.getRouteName() %></td>
            <!-- Display other driver details here -->
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="3">No Route found</td>
        </tr>
        <% } %>
    </table>
</body>
</html>
