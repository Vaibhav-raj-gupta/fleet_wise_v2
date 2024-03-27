<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Route" %>

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
    
    <form action="UpdateRouteServlet" method="post">
        <%
            // Retrieve driver object from request attribute
            Route route = (Route) request.getAttribute("Routes");
            
            // Check if driver object is not null
            if (route != null) {
        %>
            <input type="hidden" id="Route_Id" name="Route_Id" value="<%= route.getRouteId() %>">
            <label for="Route_Name">RouteName:</label>
            <input type="text" id="Route_Name" name="Route_Name" value="<%= route.getRouteName() %>" readonly><br>
            <label for="Start_Point">Start Point:</label>
            <input type="text" id="Start_Point" name="Start_Point" value="<%= route.getStartPoint() %>" readonly><br>
            <label for="Destination_Point">Destination Point:</label>
            <input type="text" id="Destination_Point" name="Destination_Point" value="<%= route.getDestinationPoint() %>" readonly><br>
            
            <input type="button" id="editButton"  value="Edit" onclick="toggleEdit()">
            <input type="submit" id="updateButton" name="action" value="Update" style="display: none;">
            <input type="submit" id="deleteButton" name="action" value="Delete">
        <% } else { %>
            <p>No driver details found.</p>
        <% } %>
    </form>
</body>
</html>
