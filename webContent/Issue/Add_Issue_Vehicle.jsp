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
        <input type="hidden" name="issueFor" value="vehicle">
        <label for="Vehicle_Id">Select Vehicle :</label><br>
        <select id="Vehicle_Id" name="Vehicle_Id" required >
        <option value="-1" disabled>Select Vehicle Name</option>
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
                    ResultSet rs = stmt.executeQuery("SELECT Vehicle_Id,registration_no FROM vehicles");

                    while(rs.next()) {
                        int Vehicle_Id = rs.getInt("Vehicle_Id");
                        String registration_no = rs.getString("registration_no");
                        out.println("<option value='" + Vehicle_Id + "'>" + registration_no + "</option>");
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