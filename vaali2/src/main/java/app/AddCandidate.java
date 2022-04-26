package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
 * Servlet implementation class AddCandidate
 */
@WebServlet("/AddCandidate")
public class AddCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao;

	@Override
	public void init() {
		String url = getServletContext().getInitParameter("connection_url_admin");
		String user = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("passwd");
		
		dao = new Dao(url, user, password);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCandidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstname = request.getParameter("firstname");
		String surname = request.getParameter("surname");
		String age = request.getParameter("age");
		String party = request.getParameter("party");
		String profession = request.getParameter("pro");
		String why = request.getParameter("why");
		String what = request.getParameter("what");
		String vote = request.getParameter("vote");

		int age_i = Integer.parseInt(age);
		int vote_i = Integer.parseInt(vote);

		if (dao.getConnection()) {
			dao.addCandidate(surname, firstname, age_i, party, profession, why, what, vote_i);

			// tästä alkaa janeten sähläys

			ArrayList<Questions> questionsList = dao.readAllQuestions();
			ArrayList<Candidates> candidatesList = dao.readAllCandidates();

			int cand = candidatesList.size();
			int ques;
			String url = getServletContext().getInitParameter("connection_url_admin");
			String user = getServletContext().getInitParameter("username");
			String password = getServletContext().getInitParameter("passwd");
			Connection conn;
			
			try {
				conn = DriverManager.getConnection(url, user, password);
				String sql = "";
				PreparedStatement stmt = conn.prepareStatement(sql);
				sql = "use vaalikone";
				stmt.executeUpdate(sql);
				
				Random rand = new Random();

				for (ques = 1; ques < questionsList.size() + 1; ques++) {

					try {
						int r = rand.nextInt(5) + 1;

						sql = "INSERT INTO answers (candidate_id, question_id, answer_int) VALUES (" + cand + "," + ques
								+ ", " + r + ");";
						stmt.executeUpdate(sql);
						
						sql = "INSERT INTO comparison (candidate_id, average) VALUES (" + cand + ", 0)";
						stmt.executeUpdate(sql);

					} catch (SQLException e) {
						System.out.println("Candidate: " + cand + " Question: " + ques + ": " + e.getMessage());

					}

				}
			} catch (SQLException e1) {
				System.out.println("insert: " + e1.getMessage());
				
			}
            
			ArrayList<Candidates> candidates = null;
			if (dao.getConnection()) {
				candidates = dao.readAllCandidates();
			} else {
				System.out.println("No connection to database");
			}
			request.setAttribute("candidates", candidates);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/EditCandidates.jsp");
			rd.forward(request, response);

		}

	}
}