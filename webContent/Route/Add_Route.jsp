<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Add Driver Form</title>
  <link rel="stylesheet" href="../Css.css">
</head>
<body>
 <div class="vehicle-form-con">
                                <div class="body-form vehicle-form">
                                    <form id="routesForm" action="RouteServlet" method="post">
                                        <div class="form-flex">
                                            <div class="form-group">
                                                <label for="routeName" class="lp">Route Name</label>
                                                <input type="text" id="routeName" name="routeName" required placeholder="Route Name">
                                            </div>
                                            <div class="form-group">
                                                <label for="startingPoint" class="lp">Starting Point</label>
                                                <input type="text" id="startingPoint" name="startingPoint" required placeholder="Starting Point">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="destinationPoint" class="lp">Destination Point</label>
                                            <input type="text" id="destinationPoint" name="destinationPoint" required placeholder="Destination Point">
                                        </div>
                                        <button type="submit" class="bbp">Add Route</button>
                                    </form>
                                    <div id="message"></div>
                                </div></div>
  <script src="add-driver.js"></script>
</body>
</html>
