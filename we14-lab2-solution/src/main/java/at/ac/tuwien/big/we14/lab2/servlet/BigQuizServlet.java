package at.ac.tuwien.big.we14.lab2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we14.lab2.api.QuizFactory;
import at.ac.tuwien.big.we14.lab2.api.impl.ServletQuizFactory;
import at.ac.tuwien.big.we14.lab2.api.impl.SimpleCategoryGenerator;
import at.ac.tuwien.big.we14.lab2.api.impl.SimpleQuestion;
import at.ac.tuwien.big.we14.lab2.api.impl.SimpleQuestionGenerator;


// Your Servlet implementation
@WebServlet(name="BigQuiz", urlPatterns= {"/we14-lab2-solution" })
public class BigQuizServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private int roundcounter;
	private int questioncounter;
	protected static Logger log = Logger.getLogger(BigQuizServlet.class);
	 @Override
	    public void init() throws ServletException {
	        super.init();
	        
	        roundcounter = -1;
	        questioncounter = -1;
	    }
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String action = request.getParameter("action");
	        
	       
			if(action==null) {
				
				PrintWriter noactionwriter = response.getWriter();
				noactionwriter.println("<html>");
				noactionwriter.println("<head>");
				noactionwriter.println("<title>Something went wrong</title>");            
				noactionwriter.println("</head>");
				noactionwriter.println("<body>");
				noactionwriter.println("<h1>QuizError: Something went wrong! No action set!</h1>");
                noactionwriter.println("</body>");
                noactionwriter.println("</html>");
				
	            return;
	        }
			
	        if(action.equals("start")) {  
	        	
	        	//Neues Spiel wird gestartet.
	        	HttpSession session = request.getSession(true);
	        	
	        	roundcounter = 1;
	        	questioncounter = 1;
	        	
	        	ServletContext servletContext = getServletContext(); 
        		QuizFactory factory = ServletQuizFactory.init(servletContext); 
        		QuestionDataProvider provider = factory.createQuestionDataProvider(); 
        		List<Category> categories = provider.loadCategoryData();
        		Question question = new SimpleQuestion();
        		
        		if(!categories.isEmpty())
        		{
        			question = (new SimpleQuestionGenerator(new SimpleCategoryGenerator(categories).getCategory()).getQuestion());
        			if(question == null)
        			{
        				log.info("question ist null");
        			}else
        			{
        				log.info("question ist nicht null");
        			}
        		}else{
        			log.info("categories leer!");
        		}
	        	
        		session.setAttribute("question", question);
	        	session.setAttribute("roundcounter", roundcounter);
	        	session.setAttribute("questioncounter", questioncounter);
	        	
	        	
	        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
	            dispatcher.forward(request, response);  
	        	
	        }else if (action.equals("questioncomplete")){
	        	///Old... and they lived in post section happy ever after
	        	
	        }else if (action.equals("roundcompleteweiter")){
	        	//sind 5 runden vergangen, dann kommt finish html, sonst kommen neue question.jsp seiten.
	        	
	        	HttpSession session = request.getSession(true);
	        	roundcounter = (int)session.getAttribute("roundcounter");
	        	questioncounter = (int)session.getAttribute("questioncounter");
	        	/*	        	
	        	if(true){
	        		PrintWriter out = response.getWriter();
	                out.println("<html>");
	                out.println("<head>");
	                out.println("<title>RoundComplete getriggert</title>");            
	                out.println("</head>");
	                out.println("<body>");
	                out.println("<h1>Hello RoundComplete getriggert"  + "</h1>");
	                out.println("</body>");
	                out.println("</html>");
	        	}	
	        	*/
	        	if(roundcounter >= 5){
	        		//finishseite aufrufen
	        		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/finish.jsp");
		            dispatcher.forward(request, response);  
		        	
	        	}else{
	        		roundcounter = roundcounter + 1;
	        		questioncounter = 1;
	        		session.setAttribute("roundcounter", roundcounter);
		        	session.setAttribute("questioncounter", questioncounter);
		        	
	        		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
		            dispatcher.forward(request, response);  
	        	}
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
	        	roundcounter = (int)session.getAttribute("roundcounter");
	        	questioncounter = (int)session.getAttribute("questioncounter");
	        	
	        	
	        	
	        	if(questioncounter >= 3){
	        		questioncounter = 1;
	        		session.setAttribute("questioncounter", questioncounter);
		        	
	        		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/roundcomplete.jsp");
		            dispatcher.forward(request, response);  
	        	}else{
	        		questioncounter = questioncounter + 1;
	        		
	        		ServletContext servletContext = getServletContext(); 
	        		QuizFactory factory = ServletQuizFactory.init(servletContext); 
	        		QuestionDataProvider provider = factory.createQuestionDataProvider(); 
	        		List<Category> categories = provider.loadCategoryData();
	        		Question question;
	        		
	        		if(!categories.isEmpty())
	        		{
	        			question = (new SimpleQuestionGenerator(new SimpleCategoryGenerator(categories).getCategory()).getQuestion());
	        			if(question == null)
	        			{
	        				
	        			}
	        		}else{
	        			return;
	        		}
	        		session.setAttribute("question", question);
	        		session.setAttribute("questioncounter", questioncounter);
	        		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
		            dispatcher.forward(request, response);  
	        	}
	        	
	        	//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
	            //dispatcher.forward(request, response);  
	        	
	        	
	        }
		 
	 }
	
	
	@Override
	public String getServletInfo() {
        return "Servlet to navigate through the game";
    }
	
}
