package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Driver;
import model.Route;
import utils.DBConnection;

import java.io.IOException;

import dao.DriverDao;
import dao.RouteDao;

/**
 * Servlet implementation class RouteServlet
 */
public class RouteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RouteServlet() {
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

			String RouteName = request.getParameter("routeName");
			String StartingPoint = request.getParameter("startingPoint");
			String DestinationPoint = request.getParameter("destinationPoint");

        try {
            // Perform database operations to create a new driver entry
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            DBConnection dbConnection = DBConnection.getDbConnnection();
            RouteDao dao = new RouteDao(dbConnection);
            Route route = new Route(0,RouteName,StartingPoint,DestinationPoint);
            Route createRoute = dao.create(route);

            if (createRoute != null) {
                // Driver entry created successfully
                // Forward the request to the JSP page to display success message or perform further actions
                request.setAttribute("createdRoute", createRoute);
                System.out.println("Data Inserted Successfully.");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/Driver/Add_driver.jsp");
//                dispatcher.forward(request, response);
            } else {
                // Failed to create driver entry
                System.out.println("Failed to create route entry.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirecting to doGet method to handle GET requests as well
        doGet(request, response);
		
	}

}
