package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Hub;
import model.Route;
import utils.DBConnection;

import java.io.IOException;

import dao.HubDao;
import dao.RouteDao;

/**
 * Servlet implementation class HubServlet
 */
public class HubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int route_id = Integer.parseInt(request.getParameter("route_id"));
		String hub_name = request.getParameter("hub_name");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		int pincode = Integer.parseInt(request.getParameter("pincode"));
		long contact_number = Long.parseLong(request.getParameter("contact_number"));
		String email_address = request.getParameter("email_address");
		

    try {
        // Perform database operations to create a new driver entry
    	Class.forName("oracle.jdbc.driver.OracleDriver");
        DBConnection dbConnection = DBConnection.getDbConnnection();
        HubDao dao = new HubDao(dbConnection);
        Hub hub = new Hub(0,route_id,hub_name,address,city,pincode,contact_number,email_address);
        Hub createHub= dao.create(hub);

        if (createHub != null) {
            // Driver entry created successfully
            // Forward the request to the JSP page to display success message or perform further actions
            request.setAttribute("createdRoute", createHub);
            System.out.println("Data Inserted Successfully.");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/Driver/Add_driver.jsp");
//            dispatcher.forward(request, response);
        } else {
            // Failed to create driver entry
            System.out.println("Failed to create route entry.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
		doGet(request, response);
	}

}
