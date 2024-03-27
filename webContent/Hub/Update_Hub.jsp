<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Hub" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Hub</title>
    <link rel="stylesheet" href="../Css.css">
</head>
<body>
    <h1>Update Hub</h1>
    <form action="UpdateHubServlet" method="post">
    
    <%-- Retrieve hub object from request attribute --%>
    <%
        Hub hub = (Hub) request.getAttribute("Hubs");
        
        // Get current route_id from the hub object
        Integer currentRouteId = hub.getRoute();
    %>
    
    <%-- Hidden input field to pass the hub ID to the servlet --%>
    <input type="hidden" id="Hub_Id" name="Hub_Id" value="<%= hub.getHubId() %>">
    
    <label for="route_id">Select New Route:</label><br>
    <select id="route_id" name="route_id" required>
        <option value="-1" disabled>Select Route ID</option>
        <!-- Dynamic generation of route options -->
        <%@ page import="java.sql.Connection"%>
        <%@ page import="java.sql.DriverManager"%>
        <%@ page import="java.sql.SQLException"%>
        <%@ page import="java.sql.Statement"%>
        <%@ page import="java.sql.ResultSet"%>
        <% 
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "assignmentUser", "root");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT Route_ID, Route_Name FROM Routes");

                while(rs.next()) {
                    int routeID = rs.getInt("Route_ID");
                    String routeName = rs.getString("Route_Name");
                    // Pre-select the option if it matches the route ID of the hub
                    String selected = currentRouteId != null && routeID == currentRouteId ? "selected" : "";
                    out.println("<option value='" + routeID + "' " + selected + ">" + routeName + "</option>");
                }
            } catch(Exception e) {
                out.println("An error occurred: " + e.getMessage());
            }
        %>
    </select><br>
    
    <label for="hub_name">Hub Name:</label><br>
    <input type="text" id="hub_name" name="hub_name" value="<%= hub.getHubName()  %>" required><br>
    
    <label for="address">Address:</label><br>
    <input type="text" id="address" name="address" value="<%= hub.getAddress() %>" required><br>
    
    <label for="city">City:</label><br>
    <input type="text" id="city" name="city" value="<%= hub.getCity() %>" required><br>
    
    <label for="pincode">Pincode:</label><br>
    <input type="text" id="pincode" name="pincode" value="<%= hub.getPincode() %>" required><br>
    
    <label for="contact_number">Contact Number:</label><br>
    <input type="text" id="contact_number" name="contact_number" value="<%= hub.getContactNumber() %>" required><br>
    
    <label for="email_address">Email Address:</label><br>
    <input type="email" id="email_address" name="email_address" value="<%= hub.getEmailAddress() %>" required><br><br>
    
    <input type="submit" value="Update">
</form>
</body>
</html>
