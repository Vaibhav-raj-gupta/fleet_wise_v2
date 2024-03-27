<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="../Css.css">
</head>
<body>
	    <h1>Report Issue</h1>
    <form action="IssueServlet" method="post">
        <input type="hidden" name="issueFor" value="consignment">
        <label for="Consignment_Id">Select Consignment :</label><br>
        <select id="Consignment_Id" name="Consignment_Id" required >
        <option value="-1" disabled>Select Consignment Name</option>
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
                    ResultSet rs = stmt.executeQuery("SELECT Consignment_Name,Consignment_Id FROM Consignments");

                    while(rs.next()) {
                        int ConsignmentID = rs.getInt("Consignment_Id");
                        String ConsignmentName = rs.getString("Consignment_Name");
                        out.println("<option value='" + ConsignmentID + "'>" + ConsignmentName + "</option>");
                    }
                } catch(Exception e) {
                    out.println("An error occurred: " + e.getMessage());
                }
            %>
        </select><br>
        
        <label for="remark">Remark:</label><br>
        <input type="text" id="remark" name="remark" required><br>
        
        <label for="description">Description:</label><br>
        <textarea id="description" name="description" rows="4" cols="50" required></textarea><br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>