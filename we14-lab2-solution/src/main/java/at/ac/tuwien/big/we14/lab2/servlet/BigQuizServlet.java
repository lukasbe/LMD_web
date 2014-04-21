package at.ac.tuwien.big.we14.lab2.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gargoylesoftware.htmlunit.javascript.host.Console;

// Your Servlet implementation
@WebServlet(name="BigQuiz", urlPatterns= {"/we15-lab2-solution" })
public class BigQuizServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String action = request.getParameter("action");
	        
	       
			if(action==null) {
	            //return;
	        }
	        if(action.equals("start")) {  
	        	
	        	HttpSession session = request.getSession(true);//vllt nicht wichtig
	        	
	        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/finish.html");
	            dispatcher.forward(request, response);  
	        	
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
