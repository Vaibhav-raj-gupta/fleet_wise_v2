<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Driver" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Driver Details</title>
    <script>
        function toggleEdit() {
            var editButton = document.getElementById('editButton');
            var updateButton = document.getElementById('updateButton');
            var deleteButton = document.getElementById('deleteButton');
            var inputs = document.getElementsByTagName('input');
            var joiningDateInput = document.getElementById('joiningDate');
            
            if (editButton.value === 'Edit') {
                if (confirm('Do you want to edit this record?')) {
                    editButton.value = 'Cancel';
                    updateButton.style.display = 'inline';
                    deleteButton.style.display = 'inline';
                    for (var i = 0; i < inputs.length; i++) {
                        inputs[i].readOnly = false;
                    }
                    joiningDateInput.readOnly = true; // Prevent editing of joining date
                }
            } else {
                editButton.value = 'Edit';
                updateButton.style.display = 'none';
                deleteButton.style.display = 'none';
                for (var i = 0; i < inputs.length; i++) {
                    inputs[i].readOnly = true;
                }
            }
        }
    </script>
</head>
<body>
    <h1>Driver Details</h1>
    
    <form action="UpdateDriverServlet" method="post">
        <%
            // Retrieve driver object from request attribute
            Driver driver = (Driver) request.getAttribute("drivers");
            
            // Check if driver object is not null
            if (driver != null) {
        %>
            <input type="hidden" id="driver_Id" name="driver_Id" value="<%= driver.getDriverId() %>">
            <label for="driverName">Name:</label>
            <input type="text" id="driverName" name="driverName" value="<%= driver.getName() %>" readonly><br>
            <label for="gender">Gender:</label>
            <input type="text" id="gender" name="gender" value="<%= driver.getGender() %>" readonly><br>
            <label for="age">Age:</label>
            <input type="text" id="age" name="age" value="<%= driver.getAge() %>" readonly><br>
            <label for="licenceNumber">License Number:</label>
            <input type="text" id="licenceNumber" name="licenceNumber" value="<%= driver.getLicenceNo() %>" readonly><br>
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" value="<%= driver.getPhoneNumber() %>" readonly><br>
            <label for="emailAddress">Email Address:</label>
            <input type="text" id="emailAddress" name="emailAddress" value="<%= driver.getEmailAddress() %>" readonly><br>
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="<%= driver.getAddress() %>" readonly><br>
            <label for="joiningDate">Joining Date:</label>
            <input type="text" id="joiningDate" name="joiningDate" value="<%= driver.getJoiningDate() %>" readonly><br>
            
            <input type="button" id="editButton"  value="Edit" onclick="toggleEdit()">
            <input type="submit" id="updateButton" name="action" value="Update" style="display: none;">
            <input type="submit" id="deleteButton" name="action" value="Delete">
        <% } else { %>
            <p>No driver details found.</p>
        <% } %>
    </form>
</body>
</html>
