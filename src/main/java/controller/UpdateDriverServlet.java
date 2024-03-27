package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Driver;
import utils.DBConnection;

import java.io.IOException;
import java.util.List;

import dao.DriverDao;

/**
 * Servlet implementation class UpdateDriverServlet
 */
public class UpdateDriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDriverServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int driverId = Integer.parseInt(request.getParameter("driver_Id")) ;
		System.out.println(driverId);
		
    	try {
            // Get driver data from the database
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            DBConnection dbConnection = DBConnection.getDbConnnection();
            DriverDao driverDao = new DriverDao(dbConnection);
            Driver drivers = driverDao.findOne(driverId);
            
            System.out.println(drivers.getAddress());
            
            request.setAttribute("drivers", drivers);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Driver/Update_driver.jsp");
            dispatcher.forward(request, response);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");

	    if (action != null && action.equals("Delete")) {
	        // Perform delete operation
	        int driverId = Integer.parseInt(request.getParameter("driver_Id"));

	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            DBConnection dbConnection = DBConnection.getDbConnnection();
	            DriverDao dao = new DriverDao(dbConnection);

	            boolean deleted = dao.delete(driverId);

	            if (deleted) {
	                // Deletion successful
	                // Forward the request to the JSP page to display success message or perform further actions
	                request.setAttribute("deleted", deleted);
	                System.out.println("Driver record deleted successfully.");
	            } else {
	                // Deletion failed
	                System.out.println("Failed to delete driver record.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } else { // Perform update operation
	        int driverId = Integer.parseInt(request.getParameter("driver_Id"));
	        String driverName = request.getParameter("driverName");
	        String gender = request.getParameter("gender");
	        int age = Integer.parseInt(request.getParameter("age"));
	        String licenceNumber = request.getParameter("licenceNumber");
	        long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
	        String emailAddress = request.getParameter("emailAddress");
	        String address = request.getParameter("address");
	        String joiningDate = request.getParameter("joiningDate");

	        try {
	            // Perform database operations to update the driver entry
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            DBConnection dbConnection = DBConnection.getDbConnnection();
	            DriverDao dao = new DriverDao(dbConnection);
	            Driver driver = new Driver(driverId, driverName, gender, age, licenceNumber, phoneNumber, emailAddress, address, joiningDate, null);
	            boolean updated = dao.update(driverId, driver);

	            if (updated) {
	                // Update successful
	                // Forward the request to the JSP page to display success message or perform further actions
	                request.setAttribute("updated", updated);
	                System.out.println("Driver record updated successfully.");
	            } else {
	                // Update failed
	                System.out.println("Failed to update driver record.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // After handling the request, redirect to doGet for further processing
	    doGet(request, response);
	}


}
