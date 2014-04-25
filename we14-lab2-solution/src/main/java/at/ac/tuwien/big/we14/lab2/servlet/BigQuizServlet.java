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

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.GameGenerator;
import at.ac.tuwien.big.we14.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we14.lab2.api.QuizFactory;
import at.ac.tuwien.big.we14.lab2.api.impl.GameBean;
import at.ac.tuwien.big.we14.lab2.api.impl.GameEntity;
import at.ac.tuwien.big.we14.lab2.api.impl.ServletQuizFactory;
import at.ac.tuwien.big.we14.lab2.api.impl.SimpleGameGenerator;

// Your Servlet implementation
@WebServlet(name = "BigQuiz", urlPatterns = { "/we14-lab2-solution" })
public class BigQuizServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private GameGenerator gameGen = new SimpleGameGenerator();
	private GameBean gameBean = new GameBean();

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			PrintWriter noactionwriter = response.getWriter();
			noactionwriter.println("<html>");
			noactionwriter.println("<head>");
			noactionwriter.println("<title>Something went wrong</title>");
			noactionwriter.println("</head>");
			noactionwriter.println("<body>");
			noactionwriter
					.println("<h1>QuizError: Something went wrong! No action set!</h1>");
			noactionwriter.println("</body>");
			noactionwriter.println("</html>");
			return;
		}

		if (action.equals("startgame")) {
			// Neues Spiel wird gestartet.
			HttpSession session = request.getSession(true);
			GameEntity gameEntity = new GameEntity();
			GameBean gameBean = new GameBean();// Multiuser

			// Diese vier Codezeilen holen alle Kategorien, Fragen und Antworten
			// aus der data.json
			ServletContext servletContext = getServletContext();
			QuizFactory factory = ServletQuizFactory.init(servletContext);
			QuestionDataProvider provider = factory
					.createQuestionDataProvider();
			List<Category> categories = provider.loadCategoryData();

			// Erstellt automatisch ein komplettes Spiel mit Kategorien
			gameGen = new SimpleGameGenerator(categories);

			int rounds = 5;
			int questioncount = 3;
			gameEntity.setGame(gameGen.generateGame(rounds, questioncount),
					gameBean);
			if (gameEntity.hasNextRound(gameBean) == true) {

				gameBean.getPlayer1RoundSummary().clear();
				gameBean.getPlayer2RoundSummary().clear();
				gameBean.setPlayer1WonRounds(0);
				gameBean.setPlayer2WonRounds(0);
				gameBean.setPlayer1RoundTime(0);
				gameBean.setPlayer2RoundTime(0);
				gameBean.setPlayer1TotalTime(0);
				gameBean.setPlayer1TotalTime(0);

				gameEntity.nextQuestion(gameEntity.nextRound(gameBean),
						gameBean);
				session.setAttribute("gameBean", gameBean);

				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/question.jsp");
				dispatcher.forward(request, response);
			} else {
				// Keine Runden mehr.
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/finish.jsp");
				dispatcher.forward(request, response);
			}
		} else if (action.equals("roundcompleteweiter")) {
			HttpSession session = request.getSession(true);

			gameBean = (GameBean) session.getAttribute("gameBean");

			GameEntity gameEntity = new GameEntity();

			if (gameEntity.hasNextRound(gameBean) == true) {

				gameBean.getPlayer1RoundSummary().clear();
				gameBean.getPlayer2RoundSummary().clear();
				gameBean.setPlayer1RoundTime(0);
				gameBean.setPlayer2RoundTime(0);

				// Es gibt noch Runden
				gameEntity.nextQuestion(gameEntity.nextRound(gameBean),
						gameBean);
				
				session.setAttribute("gameBean", gameBean);
				
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/question.jsp");
				dispatcher.forward(request, response);
			} else {
				// Keine Runden mehr.

				gameEntity.determineWinner(gameBean);

				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/finish.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			return;
		}
		if (action.equals("questioncomplete")) {

			HttpSession session = request.getSession(true);
			
			gameBean = (GameBean) session.getAttribute("gameBean");
			GameEntity gameEntity = new GameEntity();


			if (gameEntity.thisRound(gameBean).hasNext()) {
				// Es gibt noch Fragen!

				gameEntity
						.validateQuestion(gameBean.getPlayer1(),
								Integer.parseInt(request
										.getParameter("timeleftvalue")),
								request.getParameterValues("checkedChoices"),
								gameBean);

				gameEntity.nextQuestion(gameEntity.thisRound(gameBean),
						gameBean);
				session.setAttribute("gameBean", gameBean);
				
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/question.jsp");
				dispatcher.forward(request, response);

			} else {

				// GEWINNER BESTIMMEN!!

				gameEntity
						.validateQuestion(gameBean.getPlayer1(),
								Integer.parseInt(request
										.getParameter("timeleftvalue")),
								request.getParameterValues("checkedChoices"),
								gameBean);
				gameEntity.determineRoundsWinner(gameBean);

				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/roundcomplete.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	@Override
	public String getServletInfo() {
		return "Servlet to navigate through the game";
	}

}
