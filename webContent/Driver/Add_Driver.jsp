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
 <div class="body-form">
    <div class="container oc">
        <h2 class="hp">Add Driver</h2>
        <form id="addDriverForm" action="DriverServlet" method="post">
         <div class="form-flex">
            <div class="form-group">
                <label for="driverName" class="lp">Name</label>
                <input type="text" id="driverName" name="driverName" required placeholder="Name" class="li">
              </div>
              <div class="form-group">
                <label for="driverAge" class="lp">Age</label>
                <input type="number" id="driverAge" name="driverAge" required placeholder="Age" min="18" max="100">
              </div>
         </div>
         
         <div class="form-flex">
            <div class="form-group">
                <label for="driverEmail" class="lp">Email Address</label>
                <input type="email" id="driverEmail" name="driverEmail" required placeholder="Email">
              </div>
              <div class="form-group">
                <label for="driverPhone" class="lp">Phone Number</label>
                <input type="tel" id="driverPhone" name="driverPhone" required placeholder="Phone number" pattern="[0-9]{10}">
              </div>
         </div>
         <div class="form-flex">
            <div class="form-group">
                <label for="licenceNumber" class="lp">License Number</label>
                <input type="text" id="licenceNumber" name="licenceNumber" required placeholder="License number">
              </div>
              <div class="form-group">
                <label for="driverGender" class="lp">Gender</label>
                <select id="driverGender" name="driverGender" required>
                  <option value="">Select Gender</option>
                  <option value="MALE">Male</option>
                  <option value="FEMALE">Female</option>
                  <option value="OTHER">Other</option>
                </select>
              </div>
         </div>
         
         <div class="form-group">
            <label for="driverAddress" class="lp">Address</label>
            <textarea id="driverAddress" name="driverAddress" required placeholder="Address" class="li"></textarea>
          </div>
              
          
          <button type="submit" class="bbp">Add Driver</button>
        </form>
        <div id="message"></div>
      </div>
 </div>
  <script src="add-driver.js"></script>
</body>
</html>
