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
	        	
	        	
	        	
	        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
	            dispatcher.forward(request, response);  
	        	
	        }else if (action.equals("questioncomplete")){
	        	
	        	
	        }else if (action.equals("roundcompleteweiter")){
	        	//sind 5 runden vergangen, dann kommt finish html, sonst kommen neue question.jsp seiten.
	        	
	        	if(true){
	        		PrintWriter out = response.getWriter();
	                out.println("<html>");
	                out.println("<head>");
	                out.println("<title>RoundComplete getriggert</title>");            
	                out.println("</head>");
	                out.println("<body>");
	                out.println("<h1>Hello "  + "</h1>");
	                out.println("</body>");
	                out.println("</html>");
	        		
	        	}else{
	        		
	        		
	        	}
	        	
	        	
	        	
	        	//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/finish.jsp");
	            //dispatcher.forward(request, response);  
	        	//if counter == 3 -> finish.html
	        	//else roundcomplete.html
	        	
	        }
	 }
	
	 @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 	
		 String action = request.getParameter("action");
	        
	       
			if(action==null) {
	            return;
	        }
			
	        if(action.equals("questioncomplete")) { 
	        	
	        	//sind schon 3 fragen gefragt? wenn ja counter zurücksetzen und auf roundcomplete.jsp gehen
	        	
	        	HttpSession session = request.getSession(true);
	        	
	        	
	        	if(true){
	        		PrintWriter out = response.getWriter();
	                out.println("<html>");
	                out.println("<head>");
	                out.println("<title>Question Complete getriggert</title>");            
	                out.println("</head>");
	                out.println("<body>");
	                out.println("<h1>Hello " + request.getParameter("timeleftvalue") + "</h1>");
	                out.println("</body>");
	                out.println("</html>");
	        		
	        	}else{
	        		
	        		
	        	}
	        	
	        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
	            dispatcher.forward(request, response);  
	        	
	        	
	        }
		 
	 }
	
	
	@Override
	public String getServletInfo() {
        return "Servlet to navigate through the game";
    }
	
}
