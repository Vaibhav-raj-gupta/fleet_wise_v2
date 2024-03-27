package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Hub;
import utils.DBConnection;

import java.io.IOException;

import dao.HubDao;

/**
 * Servlet implementation class UpdateHubServlet
 */
public class UpdateHubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateHubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int HubId = Integer.parseInt(request.getParameter("Hub_Id")) ;
		System.out.println(HubId);
		
    	try {
            // Get Hub data from the database
    		Class.forName("oracle.jdbc.driver.OracleDriver");
            DBConnection dbConnection = DBConnection.getDbConnnection();
            HubDao HubDao = new HubDao(dbConnection);
            Hub Hubs = HubDao.findOne(HubId);
            
            System.out.println(Hubs.getRoute());
            
            request.setAttribute("Hubs", Hubs);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Hub/Update_Hub.jsp");
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
        
        int hubId = Integer.parseInt(request.getParameter("Hub_Id"));
        int routeId = Integer.parseInt(request.getParameter("route_id"));
        String hubName = request.getParameter("hub_name");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        int pincode = Integer.parseInt(request.getParameter("pincode"));
        long contactNumber = Long.parseLong(request.getParameter("contact_number"));
        String emailAddress = request.getParameter("email_address");

        try {
            // Perform database operations to update the Hub entry
            Class.forName("oracle.jdbc.driver.OracleDriver");
            DBConnection dbConnection = DBConnection.getDbConnnection();
            HubDao dao = new HubDao(dbConnection);
            Hub hub = new Hub(hubId, routeId, hubName, address, city, pincode, contactNumber, emailAddress);
            boolean updated = dao.update(hubId, hub);

            if (updated) {
                // Update successful
                // Forward the request to the JSP page to display success message or perform further actions
                request.setAttribute("updated", updated);
                System.out.println("Hub record updated successfully.");
            } else {
                // Update failed
                System.out.println("Failed to update Hub record.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // After handling the request, redirect to doGet for further processing
        doGet(request, response);
    }


}
