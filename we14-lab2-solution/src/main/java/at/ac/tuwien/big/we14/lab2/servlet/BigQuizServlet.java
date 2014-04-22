package at.ac.tuwien.big.we14.lab2.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


// Your Servlet implementation
@WebServlet(name="BigQuiz", urlPatterns= {"/we14-lab2-solution" })
public class BigQuizServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	 @Override
	    public void init() throws ServletException {
	        super.init();
	        
	    }
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String action = request.getParameter("action");
	        
	       
			if(action==null) {
	            return;
	        }
			
	        if(action.equals("start")) {  
	        	
	        	HttpSession session = request.getSession(true);//vllt nicht wichtig
	        	
	        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
	            dispatcher.forward(request, response);  
	        	
	        }else if (action.equals("question")){
	        	
	        	if(true){
	        		
	        		
	        	}else{
	        		
	        		
	        	}
	        	
	        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
	            dispatcher.forward(request, response);  
	        	
	        }else if (action.equals("roundcomplete")){
	        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/finish.jsp");
	            dispatcher.forward(request, response);  
	        	//if counter == 3 -> finish.html
	        	//else roundcomplete.html
	        	
	        }
	 }
	
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

		 
	 }
	
	
	@Override
	public String getServletInfo() {
        return "Servlet to navigate through the game";
    }
	
}
