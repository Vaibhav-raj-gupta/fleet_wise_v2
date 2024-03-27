<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Hub</title>
    <link rel="stylesheet" href="../Css.css">
</head>
<body>
    <h1>Add New Hub</h1>
    <form action="HubServlet" method="post">
        <label for="route_id">Select Route:</label><br>
        <select id="route_id" name="route_id" required >
        <option value="-1" disabled>Select Route Name</option>
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
                    ResultSet rs = stmt.executeQuery("SELECT Route_ID,Route_Name FROM Routes");

                    while(rs.next()) {
                        int routeID = rs.getInt("Route_ID");
                        String routeName = rs.getString("Route_Name");
                        out.println("<option value='" + routeID + "'>" + routeName + "</option>");
                    }
                } catch(Exception e) {
                    out.println("An error occurred: " + e.getMessage());
                }
            %>
        </select><br>
        
        <label for="hub_name">Hub Name:</label><br>
        <input type="text" id="hub_name" name="hub_name" required><br>
        
        <label for="address">Address:</label><br>
        <input type="text" id="address" name="address" required><br>
        
        <label for="city">City:</label><br>
        <input type="text" id="city" name="city" required><br>
        
        <label for="pincode">Pincode:</label><br>
        <input type="text" id="pincode" name="pincode" required><br>
        
        <label for="contact_number">Contact Number:</label><br>
        <input type="text" id="contact_number" name="contact_number" required><br>
        
        <label for="email_address">Email Address:</label><br>
        <input type="email" id="email_address" name="email_address" required><br><br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>

    