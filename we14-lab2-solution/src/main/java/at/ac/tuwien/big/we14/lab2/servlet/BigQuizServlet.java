package at.ac.tuwien.big.we14.lab2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Your Servlet implementation

public class BigQuizServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String action = request.getParameter("action");
	        if(action==null) {
	            return;
	        }
	        if(action.equals("start")) {     
	        	
	        	
	        }else if (action.equals("question")){
	        
	        	
	        }else if (action.equals("roundcomplete")){
	        
	        	//if counter == 3 -> finish.html
	        	//else roundcomplete.html
	        	
	        }
	 }
	
	@Override
	public String getServletInfo() {
        return "Servlet to navigate through the game";
    }
	
}