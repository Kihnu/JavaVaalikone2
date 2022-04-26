package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Dao;
import data.Candidates;
import data.Questions;

/**
 * Servlet implementation class AdminRefresh
 */
@WebServlet("/AdminRefresh")
public class AdminRefresh extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = null;

	@Override
	public void init() {
		String url = getServletContext().getInitParameter("connection_url_admin");
		String user = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("passwd");

		dao = new Dao(url, user, password);
	}

	public AdminRefresh() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Questions> questionsList = null;
		ArrayList<Candidates> candidatesList = null;
		int rowsInserted;

		if (dao.getConnection()) {

			String sql = "";
			// Miten ottaa DAOsta
//			String dbURL = "jdbc:mysql://localhost:3306/";
//			String username = "user";
//			String password = "password";
			String url = getServletContext().getInitParameter("connection_url_admin");
			String user = getServletContext().getInitParameter("username");
			String password = getServletContext().getInitParameter("passwd");

			try {
				Connection conn = DriverManager.getConnection(url, user, password);

				if (conn != null) {
					System.out.println("Connected!");
				}

				PreparedStatement statement = conn.prepareStatement(sql);

				sql = "DROP DATABASE if exists Vaalikone;"; // Poistaa mahdollisen databasen
				statement.executeUpdate(sql);

				sql = "CREATE DATABASE Vaalikone;"; // Luo uudestaan Vaalikone DB:n
				statement.executeUpdate(sql);

				sql = "use Vaalikone;"; // Joo
				statement.executeUpdate(sql);

				sql = "CREATE TABLE candidates (candidate_id int(3) NOT NULL AUTO_INCREMENT, surname varchar(30) NOT NULL, "
						+ "firstname varchar(30), age int(3), party varchar(30) NOT NULL, profession varchar(60), why varchar(600), "
						+ "what varchar(600), vote_nro int(4) NOT NULL, PRIMARY KEY (candidate_id, vote_nro));";
				statement.executeUpdate(sql);

//				// Kuva sarake lisääminen
//				sql = "CREATE TABLE candidates (candidate_id int(3) NOT NULL AUTO_INCREMENT, surname varchar(30) NOT NULL, "
//						+ "firstname varchar(30), age int(3), party varchar(30) NOT NULL, profession varchar(60), why varchar(600), "
//						+ "what varchar(600), vote_nro int(4) NOT NULL, PRIMARY KEY (candidate_id, vote_nro), kuva_id longblob NOT NULL);";
//				statement.executeUpdate(sql);

				// Quesiton tablen luonti
				sql = "CREATE TABLE questions (question_id int(3) NOT NULL AUTO_INCREMENT, question varchar(600) NOT NULL, "
						+ "PRIMARY KEY (question_id));";
				statement.executeUpdate(sql);

				// Vastaus tablen luonti
				sql = "CREATE TABLE answers (answer_id int(5) NOT NULL AUTO_INCREMENT, candidate_id int(3) NOT NULL, "
						+ "question_id int(3) NOT NULL, answer_int int(1) NOT NULL, answer_string varchar(600), PRIMARY KEY (answer_id));";
				statement.executeUpdate(sql);

				// Vertailu tablen luonti
				sql = "CREATE TABLE comparison (comp_id int(5) NOT NULL AUTO_INCREMENT, candidate_id int(3) NOT NULL, average int(3) NOT NULL, PRIMARY KEY (comp_id));";
				statement.executeUpdate(sql);

				// Candidates lisäys
				sql = "INSERT INTO  candidates (surname, firstname, age, party, profession, why, what, vote_nro) VALUES (\"Duck\", \"Donald\", 36, \"Socialist\", \"Rubber bread\", \"In my opinion the amount of work an anthropomorphic animal has to do just to live their life normally is way too much.\", \"Something to occupy my feeble mind while I try to avoid working as much as I can.\", 313);";
				statement.executeUpdate(sql);

				sql = "INSERT INTO  candidates (surname, firstname, age, party, profession, why, what, vote_nro) VALUES (\"McDuck\", \"Scrooge\", 75, \"Indepentend\", \"Business magnate\", \"I need money\", \"Money\", 112);";
				statement.executeUpdate(sql);

				sql = "INSERT INTO  candidates (surname, firstname, age, party, profession, why, what, vote_nro) VALUES (\"Gander\", \"Gladstone\", 38, \"Republican\", \"\", \"I just take part of every competition I can find, I'm so lucky that if I win, I win, and if I lose, I know I dodged a bullet, so it's always a win-win situation for me.\", \"It doesn't matter if I win, as long as Donald (Duck) loses.\", 777);";
				statement.executeUpdate(sql);

				sql = "INSERT INTO  candidates (surname, firstname, age, party, profession, why, what, vote_nro) VALUES (\"Duck\", \"Daisy\", 35, \"Enviromentalist\", \"TV Reporter\", \"Someone needs to do something about the flowers in the middle of the park, they are dying!\", \"My promise is to fix the flowers and then I'll probably resign.\", 666);";
				statement.executeUpdate(sql);

				sql = "INSERT INTO  candidates (surname, firstname, age, party, profession, why, what, vote_nro) VALUES (\"Gearloose\", \"Gyro\", 50, \"Libertarian\", \"Inventor\", \"My inventions will bring a new brighter future for everyone in Duckburg.\", \"To make the living conditions of your every day citizens easier and more stress free. To automate most of the working places, so that people can roam free daily without selling their souls to faceless corporations.\", 314);";
				statement.executeUpdate(sql);

				sql = "INSERT INTO  candidates (surname, firstname, age, party, profession, why, what, vote_nro) VALUES (\"Mouse\", \"Mickey\", 47, \"Socialist\", \"Jack-of-all-trades\", \"I am the most reliable person you will ever meet!\", \"I want to be part of recreating the glory of Duckburg that I remember we had when I was just a wee lad.\", 420);";
				statement.executeUpdate(sql);

				sql = "INSERT INTO  candidates (surname, firstname, age, party, profession, why, what, vote_nro) VALUES (\"Mouse\", \"Minnie\", 47, \"Republican\", \"Barmaid\", \"I feel like no one else gets things done. No one gets things done better than I do, go ask anyone. Ask me for example.\", \"Make Duckburg Great Again\", 137);";
				statement.executeUpdate(sql);

				sql = "INSERT INTO  candidates (surname, firstname, age, party, profession, why, what, vote_nro) VALUES (\"Pluto\", \"\", 8, \"Anarchist\", \"Dog\", \"woof\", \"woof woof\", 100);";
				statement.executeUpdate(sql);

				sql = "INSERT INTO  candidates (surname, firstname, age, party, profession, why, what, vote_nro) VALUES (\"Duck\", \"Huey, Dewey and Louie\", 11, \"Pirate Party\", \"3 children in a trenchcoat\", \"We... I mean, I feel like schools give too much homework for us... I mean, the children, and also recess is way too short, it should be over 1 hour long.\", \"Free candy for every children in Duckburg and never having to go to school again!\", 333);";
				statement.executeUpdate(sql);

				sql = "INSERT INTO  candidates (surname, firstname, age, party, profession, why, what, vote_nro) VALUES (\"Goof\", \"G.G. Goofy\", 61, \"Socialist\", \"Comedian\", \"Why is anyone anywhere? What is the point of life? Are we all just puppets here for the entertainment of some greater intergalactic cosmic beign that plays with the lives and minds of the innocent? What was the question?\", \"I don't know, PlayStation 5 or something.\", 123);";

				rowsInserted = statement.executeUpdate(sql);
				if (rowsInserted > 0) {
					System.out.println("Candidates were updated successfully!");
				} else {
					System.out.println("Nothing happened");
				}

				// Kysymysten lisäys
				sql = "INSERT INTO questions (question) VALUES (\"Duckburg, Mouseville and Goosetown should all be combined into one big city.\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"The roads of Duckburg are in good condition.\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"The city of Duckburg should add more public transportation options.\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"Duckburg needs more car repair shops.\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"SAMPLE TEXT\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"Fethry Duck is a menace to society.\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"Schools should adapt more to the distance education model.\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"We live in a society.\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"Scrooge McDuck's Money Bin should be moved away from Duckburg.\"); ";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"I don't agree with this statement.\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"Somebody once told me the world is gonna roll me.\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"Duckburg has too many ducks.\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"Every job in Duckburg should be automated.\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"The ducks and other birdlike citizens of Duckberg should be able to fly.\");";
				statement.executeUpdate(sql);
				sql = "INSERT INTO questions (question) VALUES (\"Dark chocolate is better than white chocolate.\");";

				rowsInserted = statement.executeUpdate(sql);
				if (rowsInserted > 0) {
					System.out.println("Questions were updated successfully!");
				} else {
					System.out.println("Nothing happened");
				}

				// Answers lisäys
				Random rand = new Random();
				questionsList = dao.readAllQuestions();
				candidatesList = dao.readAllCandidates();
				int cand;
				int ques;

				for (cand = 1; cand < candidatesList.size() + 1; cand++) {
					for (ques = 1; ques < questionsList.size() + 1; ques++) {
						try {
							int r = rand.nextInt(5) + 1;
							sql = "INSERT INTO answers (candidate_id, question_id, answer_int) VALUES (" + cand + ", "
									+ ques + ", " + r + ");";
							statement.executeUpdate(sql);
						} catch (SQLException e) {
							System.out.println("Answers " + cand + " - " + ques + ": " + e.getMessage());
						}

					}
				}

				for (cand = 1; cand < candidatesList.size() + 1; cand++) {
					sql = "INSERT INTO comparison (candidate_id, average) VALUES (" + cand + ", 0)";
					statement.executeUpdate(sql);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		} else {
			System.out.println("No connection to database");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/AdminMain");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
