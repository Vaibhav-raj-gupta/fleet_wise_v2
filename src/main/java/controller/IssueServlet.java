package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Issue;
import utils.DBConnection;

import java.io.IOException;

import dao.IssueDao;

/**
 * Servlet implementation class IssueServlet
 */
public class IssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueServlet() {
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
		
        String issueFor = request.getParameter("issueFor");
        int consignmentId;
        int vehicleId;
        
        if (request.getParameter("Consignment_Id") != null) {
        	consignmentId = Integer.parseInt(request.getParameter("Consignment_Id")) ;
        } else {
            // If the parameter is not present, assign a default value or handle it as needed
        	consignmentId = -1; // Or any other default value that makes sense in your context
        }

        // Check if the parameter for vehicleId is present in the request
        if (request.getParameter("Vehicle_Id") != null) {
            vehicleId = Integer.parseInt(request.getParameter("Vehicle_Id"));
        } else {
            // If the parameter is not present, assign a default value or handle it as needed
            vehicleId = -1; // Or any other default value that makes sense in your context
        }
        String remark = request.getParameter("remark");
        String description = request.getParameter("description");
        
        if ("consignment".equals(issueFor)) {

            try {
    	        // Perform database operations to create a new driver entry
    	    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	        DBConnection dbConnection = DBConnection.getDbConnnection();
    	        IssueDao dao = new IssueDao(dbConnection);
    	        Issue Issue = new Issue(0,consignmentId,1008,description,"1","1",remark,"1");
    	        Issue createIssue= dao.create(Issue);

    	        if (createIssue != null) {
    	            // Driver entry created successfully
    	            // Forward the request to the JSP page to display success message or perform further actions
    	            request.setAttribute("createdIssue", createIssue);
    	            System.out.println("Data Inserted Successfully.");
//    	            RequestDispatcher dispatcher = request.getRequestDispatcher("/Driver/Add_driver.jsp");
//    	            dispatcher.forward(request, response);
    	        } else {
    	            // Failed to create driver entry
    	            System.out.println("Failed to create route entry.");
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
        	
        } 
        
         if ("vehicle".equals(issueFor)) {

        	
            try {
    	        // Perform database operations to create a new driver entry
    	    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	        DBConnection dbConnection = DBConnection.getDbConnnection();
    	        IssueDao dao = new IssueDao(dbConnection);
    	        Issue Issue = new Issue(0,description,vehicleId,1008,"1","1",remark,"1");
    	        Issue createIssue= dao.create1(Issue);

    	        if (createIssue != null) {
    	            // Driver entry created successfully
    	            // Forward the request to the JSP page to display success message or perform further actions
    	            request.setAttribute("createdIssue", createIssue);
    	            System.out.println("Data Inserted Successfully.");
//    	            RequestDispatcher dispatcher = request.getRequestDispatcher("/Driver/Add_driver.jsp");
//    	            dispatcher.forward(request, response);
    	        } else {
    	            // Failed to create driver entry
    	            System.out.println("Failed to create route entry.");
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
        }
  
		doGet(request, response);
		
		
	}

}
