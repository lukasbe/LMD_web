package at.ac.tuwien.big.we14.lab2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import at.ac.tuwien.big.we14.lab2.api.Answer;
import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.Choice;
import at.ac.tuwien.big.we14.lab2.api.GameGenerator;
import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we14.lab2.api.QuizFactory;
import at.ac.tuwien.big.we14.lab2.api.impl.GameBean;
import at.ac.tuwien.big.we14.lab2.api.impl.GameEntity;
import at.ac.tuwien.big.we14.lab2.api.impl.ServletQuizFactory;
import at.ac.tuwien.big.we14.lab2.api.impl.SimpleAnswer;
import at.ac.tuwien.big.we14.lab2.api.impl.SimpleGameGenerator;
import at.ac.tuwien.big.we14.lab2.api.impl.SimpleQuestionGenerator;


// Your Servlet implementation
@WebServlet(name="BigQuiz", urlPatterns= {"/we14-lab2-solution" })
public class BigQuizServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected static Logger log = Logger.getLogger(BigQuizServlet.class);
	private GameGenerator gameGen = new SimpleGameGenerator();
	private GameEntity gameEntity = new GameEntity();
	private GameBean gameBean = new GameBean();
	
	//private Question question = new SimpleQuestion();
	//private Category category = new SimpleCategory();
	
	//private SimpleCategoryGenerator catGen = new SimpleCategoryGenerator();
	//private QuestionGenerator questionGen = new SimpleQuestionGenerator();
	
	
	
	 @Override
	    public void init() throws ServletException {
	        super.init();
	        log.info("initialisiert");
	        
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
	        	log.info("Action: Start");
	        	//Neues Spiel wird gestartet.
	        	HttpSession session = request.getSession(true);
	        	GameEntity gameEntity = new GameEntity();
	        	GameBean gameBean = new GameBean();//Multiuser
	        	
	        	
	        	//Diese vier Codezeilen holen alle Kategorien, Fragen und Antworten aus der data.json
	        	ServletContext servletContext = getServletContext(); 
        		QuizFactory factory = ServletQuizFactory.init(servletContext); 
        		QuestionDataProvider provider = factory.createQuestionDataProvider(); 
        		List<Category> categories = provider.loadCategoryData();
        		
        		
        		log.info("neues Spiel wird erstellt:");
        		// Erstellt automatisch ein komplettes Spiel mit Kategorien
        		gameGen = new SimpleGameGenerator(categories);
        		log.info("simple generatior ist aus");
        		int rounds = 5;
        		int questioncount = 3;
        		gameEntity.setGame(gameGen.generateGame(rounds, questioncount), gameBean);
        		log.info("neues spiel wurde erstellt.Alles sollte zufällig sein");
        		if(gameEntity.hasNextRound(gameBean) == true){
        			
        			/*
        			List<Boolean> lis = new ArrayList<Boolean>();
        			lis.add(true);
        			lis.add(false);
        			lis.add(false);
        			
        			List<Boolean> lis2 = new ArrayList<Boolean>();
        			lis2.add(true);
        			lis2.add(true);
        			lis2.add(false);
        			
        			gameBean.setPlayer1RoundSummary(lis);
        			gameBean.setPlayer2RoundSummary(lis2);
        			session.setAttribute("gameBean",gameBean);
        			*/
        			
        			
        			log.info("es gibt eine nächste Runde");
        			//Question question = gameEntity.nextRound().next();
        			gameEntity.nextQuestion(gameEntity.nextRound(gameBean),gameBean);
        			log.info("Es wurden die Questions eingelesen");
        			//session.setAttribute("gameEntity", gameEntity);
        			session.setAttribute("gameBean", gameBean);
        			log.info("bean im session gespeichert");
        			//session.setAttribute("question", question);
        			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
    	            dispatcher.forward(request, response);
        		}else{
        			//Keine Runden mehr.
        			log.info("es gibt keine nächste Runde");
        			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/finish.jsp");
		            dispatcher.forward(request, response);
        			
        		}
        		
        		
        		
	        	
	        	
	        }else if (action.equals("questioncomplete")){
	        	log.info("Action: in GET questioncomplete");
	        	///Old... and they lived in post section happy ever after
	        	
	        }else if (action.equals("roundcompleteweiter")){
	        	log.info("Action: roundcomplete");
	        	HttpSession session = request.getSession(true);
	        	
	        	//gameEntity = (GameEntity) session.getAttribute("gameEntity");
        		gameBean = (GameBean) session.getAttribute("gameBean");
	        	
        		GameEntity gameEntity = new GameEntity();
        		
	        	log.info("spiel wurde aus dem session geladen");
	        	
	        	
	        	if(gameEntity.hasNextRound(gameBean) == true){
        			//Es gibt noch Runden
        			log.info("es gibt eine nächste Runde");
        			//Question question = gameEntity.nextRound().next();
        			gameEntity.nextQuestion(gameEntity.nextRound(gameBean),gameBean);
        			log.info("Es wurden die Questions eingelesen");
        			//session.setAttribute("gameEntity", gameEntity);
        			session.setAttribute("gameBean",gameBean);
        			log.info("bean im session gespeichert");
        			//session.setAttribute("question", question);
        			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
    	            dispatcher.forward(request, response);
        		}else{
        			//Keine Runden mehr.
        			log.info("es gibt keine nächste Runde");
        			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/finish.jsp");
		            dispatcher.forward(request, response);
        			
        		}      	
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
	        	
	        	HttpSession session = request.getSession(true);
	        	log.info("Action: question complete");
	        	
	        	       		
	        	//gameEntity = (GameEntity) session.getAttribute("gameEntity");
        		gameBean = (GameBean) session.getAttribute("gameBean");
	        	GameEntity gameEntity = new GameEntity();
        		
        		
	        	log.info("spiel wurde aus dem session geladen");
        		
        		if(gameEntity.thisRound(gameBean).hasNext()){
        			//Es gibt noch Fragen!
        			
        			log.info("submitted time: "+request.getParameter("timeleftvalue"));
        			
        			
        			log.info("parameter: "+ request.getParameterNames().toString());
        			log.info("parametervalues: "+ request.getParameterValues("checkedChoices"));
        			        			
        			gameEntity.validateQuestion(gameBean.getPlayer1(), Integer.parseInt(request.getParameter("timeleftvalue")),request.getParameterValues("checkedChoices"),gameBean);
        			
        			
        			
        			
        			//ans.setTickedHackerl(tickedhackerl);
        			
        			
        			log.info("es gibt eine nächste Frage");
        			//Question question = gameEntity.thisRound().next();
        			gameEntity.nextQuestion(gameEntity.thisRound(gameBean),gameBean);
        			log.info("Es wurden die (nicht ersten) Questions eingelesen");
        			//session.setAttribute("gameEntity", gameEntity);
        			session.setAttribute("gameBean",gameBean);
        			log.info("bean im session gespeichert");
        			//session.setAttribute("question", question);
        			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/question.jsp");
    	            dispatcher.forward(request, response);
        			
        		}else { 
        			
        			//GEWINNER BESTIMMEN!!
        			
        			
        			gameEntity.validateQuestion(gameBean.getPlayer1(), Integer.parseInt(request.getParameter("timeleftvalue")),request.getParameterValues("checkedChoices"),gameBean);
        			
        			
        			
        			/*
        			List<Boolean> lis = new ArrayList<Boolean>();
        			lis.add(true);
        			lis.add(false);
        			lis.add(false);
        			
        			List<Boolean> lis2 = new ArrayList<Boolean>();
        			lis2.add(true);
        			lis2.add(true);
        			lis2.add(false);
        			
        			
        			
        			
        			gameBean.setPlayer1RoundSummary(lis);
        			gameBean.setPlayer2RoundSummary(lis2);
        			session.setAttribute("gameBean",gameBean);
        			*/
        			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/roundcomplete.jsp");
    	            dispatcher.forward(request, response);       			
        		}
	        }
	 }
	
	
	@Override
	public String getServletInfo() {
        return "Servlet to navigate through the game";
    }
	
}
