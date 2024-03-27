<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Hub" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hub List</title>
</head>
<body>
	<button><a href="HubServlet">refresh</a></button>
    <h1>Hub List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <!-- Add more columns as needed -->
        </tr>
        <% 
         List<Hub> Hub = (List<Hub>) request.getAttribute("Hubs");
            if (Hub != null && !Hub.isEmpty()) {
                for (Hub Hubs : Hub) {
        %>
        <tr>
            <td><a href="UpdateHubServlet?Hub_Id=<%= Hubs.getHubId() %>"><%= Hubs.getHubId() %></a></td>
            <td><%= Hubs.getHubName() %></td>
            <!-- Display other driver details here -->
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="3">No Hub found</td>
        </tr>
        <% } %>
    </table>
</body>
</html>
