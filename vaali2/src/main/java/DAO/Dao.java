package DAO;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import data.AnswersC;
import data.Candidates;
import data.Questionsvanha;
import data.Comparison;

import java.sql.Connection;
import data.SingleCandidateAnswers;

/**
 * @author Toni, Erik & Janette
 *
 */
public class Dao {
	private String user;
	private String pass;
	private String url;
	private Connection conn;

	public Dao(String url, String user, String pass) {
		this.url = "jdbc:mysql://localhost:3306/vaalikone?useSSL=false";
		this.user = "user";// KOVAKOODIA
		this.pass = "password";
	}

	public boolean getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					throw new SQLException(e);
				}
				conn = DriverManager.getConnection(url, user, pass);
			}
			return true;
		} catch (SQLException e) {
			System.out.println("DAO get connection " + e.getMessage());
			return false;
		}
	}

	/**
	 * Reads all the questions so the voter can read and answer them.
	 * 
	 * @return returns every question to a list.
	 */
	public ArrayList<Questionsvanha> readAllQuestions() {
		ArrayList<Questionsvanha> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "";
			PreparedStatement statement = conn.prepareStatement(sql);
			sql = "use vaalikone";
			statement.executeUpdate(sql);
			ResultSet RS = stmt.executeQuery("select * from questions");
			while (RS.next()) {
				Questionsvanha q = new Questionsvanha();
				q.setId(RS.getInt("question_id"));
				q.setQuestion(RS.getString("question"));
				list.add(q);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Read all questions: " + e.getMessage());
			return null;
		}

	}

	/**
	 * Reads all the candidates and their informations.
	 * 
	 * @return returns every candidate to a list.
	 */
	public ArrayList<Candidates> readAllCandidates() {
		ArrayList<Candidates> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "";
			PreparedStatement statement = conn.prepareStatement(sql);
			sql = "use vaalikone";
			statement.executeUpdate(sql);
			ResultSet RS = stmt.executeQuery("select * from candidates");
			while (RS.next()) {
				Candidates c = new Candidates();
				c.setId(RS.getInt("candidate_id"));
				c.setfirstname(RS.getString("firstname"));
				c.setsurname(RS.getString("surname"));
				c.setParty(RS.getString("party"));
				c.setAge(RS.getInt("age"));
				c.setProfession(RS.getString("profession"));
				c.setWhat(RS.getString("what"));
				c.setWhy(RS.getString("why"));
				c.setVote_nro(RS.getInt("vote_nro"));
				list.add(c);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Read all candidates: " + e.getMessage());
			return null;
		}

	}

	/**
	 * Reads a certain one candidate.
	 * 
	 * @param id is the unique number of the candidate.
	 * @return returns a candidate with a certain id.
	 */
	public Candidates readCertainCandidate(String id) {
		Candidates c = null;
		try {
			Statement stmt = conn.createStatement();
			String sql = "use vaalikone;";
			stmt.executeUpdate(sql);
			sql = "select * from candidates where candidate_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet RS = pstmt.executeQuery();
			// Tietyn kandidaatin tiedot
			while (RS.next()) {
				c = new Candidates();
				c.setId(RS.getInt("candidate_id"));
				c.setfirstname(RS.getString("firstname"));
				c.setsurname(RS.getString("surname"));
				c.setParty(RS.getString("party"));
				c.setAge(RS.getInt("age"));
				c.setProfession(RS.getString("profession"));
				c.setWhat(RS.getString("what"));
				c.setWhy(RS.getString("why"));
				c.setVote_nro(RS.getInt("vote_nro"));
			}
			return c;
		} catch (SQLException e) {
			System.out.println("Read certain candidates: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Reads all the answers the candidates have given.
	 * 
	 * @return returns every answer from the candidates.
	 */
	public ArrayList<AnswersC> readAllAnswersC() {
		ArrayList<AnswersC> list = new ArrayList<>();
		try {

			Statement stmt = conn.createStatement();
			ResultSet RS = stmt.executeQuery("select * from answers");
			while (RS.next()) {
				AnswersC a = new AnswersC();
				a.setanswer_id(RS.getInt("answer_id"));
				a.setCandidate_id(RS.getInt("candidate_id"));
				a.setQuestion_id(RS.getInt("question_id"));
				a.setanswer_int(RS.getInt("answer_int"));
				a.setanswer_string(RS.getString("answer_string"));

				list.add(a);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Read all answers C: " + e.getMessage());
			return null;
		}

	}

	/**
	 * Reads a certain answer from the candidates.
	 * 
	 * @param id is the unique number for answers.
	 * @return returns certains answers from candidates.
	 */
	public ArrayList<Integer> readCertainAnswersC(int id) {
		ArrayList<Integer> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "use vaalikone;";
			stmt.executeUpdate(sql);
			sql = "select * from answers where candidate_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet RS = pstmt.executeQuery();
			while (RS.next()) {
				list.add(RS.getInt("answer_int"));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Read some answers: " + e.getMessage());
			return null;
		}

	}
	
	public ArrayList<Integer> readCandidateId() {
		ArrayList<Integer> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "use vaalikone;";
			stmt.executeUpdate(sql);
			ResultSet RS = stmt.executeQuery("select candidate_id from candidates");
			while (RS.next()) {
				list.add(RS.getInt("candidate_id"));
			}
			System.out.println("readCandID: " + list);
			return list;
		} catch (SQLException e) {
			System.out.println("Read candidate id: " + e.getMessage());
			return null;
		}

	}

	/**
	 * Adds the calculated numbers for the candidates.
	 * 
	 * @param candidate is the unique id for a candidate.
	 * @param average   is the number of how many percent the candidate and voter
	 *                  agree with each others.
	 * @return returns the percentage to a list.
	 */
	public ArrayList<Integer> addComparison(int candidate, int average) {
		ArrayList<Integer> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "use vaalikone;";
			stmt.executeUpdate(sql);
			sql = "update comparison set average = " + average + " where candidate_id = " + candidate + ";";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("comparison: " + e.getMessage());
			return null;
		}
		return list;
	}

	/**
	 * Updates already existing candidate's information.
	 * 
	 * @param c is the candidate that is going to be added.
	 * @return returns all candidates with updated information.
	 */
	public ArrayList<Candidates> updateCandidate(Candidates c) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "use vaalikone;";
			stmt.executeUpdate(sql);
			sql = "update candidates set firstname=?, surname=?, age=?, party=?, profession=?, why=?, what=?, vote_nro=? where candidate_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getfirstname());
			pstmt.setString(2, c.getsurname());
			pstmt.setInt(3, c.getAge());
			pstmt.setString(4, c.getParty());
			pstmt.setString(5, c.getProfession());
			pstmt.setString(6, c.getWhy());
			pstmt.setString(7, c.getWhat());
			pstmt.setInt(8, c.getVote_nro());
			pstmt.setInt(9, c.getId());
			pstmt.executeUpdate();
			return readAllCandidates();
		} catch (SQLException e) {
			System.out.println("update candidate: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Deletes a candidate from the database.
	 * 
	 * @param id is the unique number for the candidate.
	 * @return returns all the candidates that are left.
	 */
	public ArrayList<Candidates> deleteCandidate(String id) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "use vaalikone;";
			stmt.executeUpdate(sql);
			sql = "delete from answers where candidate_id=" + id;
			stmt.executeUpdate(sql);
			sql = "delete from comparison where candidate_id=" + id;
			stmt.executeUpdate(sql);
			sql = "delete from candidates where candidate_id=" + id;
			stmt.executeUpdate(sql);
			autoIncrement("candidates");
			return readAllCandidates();
		} catch (SQLException e) {
			System.out.println("Deletoi: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Reads all the comparison numbers and orders them from the highest number to
	 * the lowest.
	 * 
	 * @return returns every comparison between the voter and candidates to a list.
	 */
	public ArrayList<Comparison> readAllComparison() {
		ArrayList<Comparison> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "";
			PreparedStatement statement = conn.prepareStatement(sql);
			sql = "use vaalikone";
			statement.executeUpdate(sql);
			ResultSet RS = stmt.executeQuery(
					"select * from candidates join comparison on candidates.candidate_id = comparison.comp_id order by average desc;");
			while (RS.next()) {
				Comparison c = new Comparison();
				c.setComparisonID(RS.getInt("comp_id"));
				c.setId(RS.getInt("candidate_id"));
				c.setComparisonPercent(RS.getInt("average"));
				c.setFirstname(RS.getString("firstname"));
				c.setLastname(RS.getString("surname"));
				c.setParty(RS.getString("party"));
				c.setVote(RS.getInt("vote_nro"));
				c.setC_id(RS.getInt("candidate_id"));
				list.add(c);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Read all comparisons: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Adds a new candidate.
	 * 
	 * @param surname    is the surname of the candidate.
	 * @param firstname  is the first name of the candidate.
	 * @param age        is the age of the candidate.
	 * @param party      is the party of the candidate.
	 * @param profession is the profession of the candidate.
	 * @param why        is why the candidate is running for the office.
	 * @param what       is what the candidate wants to do when they get elected.
	 * @param vote_nro   is the number you will be voting for.
	 * @return returns all the candidates to a list with the new candidate.
	 */
	public ArrayList<Candidates> addCandidate(String surname, String firstname, int age, String party,
			String profession, String why, String what, int vote_nro) {
		try {
			String sql = "";
			PreparedStatement stmt = conn.prepareStatement(sql);
			sql = "use vaalikone";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO candidates (surname, firstname, age, party, profession, why, what, vote_nro) VALUES (\""
					+ surname + "\", \"" + firstname + "\", " + age + ", \"" + party + "\", \"" + profession + "\", \""
					+ why + "\", \"" + what + "\", " + vote_nro + ");";
			stmt.executeUpdate(sql);
			return readAllCandidates();
		} catch (SQLException e) {
			System.out.println("Add candidate: " + e.getMessage());
			return null;
		}

	}

	// Tietylle kandidaatille kysymykset ja niille vastaukset
	/**
	 * Reads a certain candidate and all the answers for them.
	 * 
	 * @param id is the id for the candidate.
	 * @return returns the questions and candidates to a list.
	 */
	public ArrayList<SingleCandidateAnswers> readCertainCandidates(String id) {
		ArrayList<SingleCandidateAnswers> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "";
			PreparedStatement statement = conn.prepareStatement(sql);
			sql = "use vaalikone";
			statement.executeUpdate(sql);
			// Right join SQL
			ResultSet RS = stmt.executeQuery(
					"select questions.question, answers.answer_int FROM answers RIGHT JOIN questions ON answers.question_id = questions.question_id WHERE candidate_id = "
							+ id + ";");
			while (RS.next()) {
				SingleCandidateAnswers c = new SingleCandidateAnswers();
				c.setQuestion(RS.getString("question"));
				c.setAnswer_int(RS.getInt("answer_int"));
				// System.out.println(c);
				list.add(c);
			}
			// System.out.println(list);
			return list;
		} catch (SQLException e) {
			System.out.println("Certain candidates questions + answers: " + e.getMessage());
			return null;
		}

	}

	public void autoIncrement(String command) {
//		try {
//			conn = DriverManager.getConnection(url, user, pass);
//			String sql = "";
//			PreparedStatement statement = conn.prepareStatement(sql);
//			sql = "use vaalikone";
//			statement.executeUpdate(sql);
//			sql = "alter table " + command + " auto_increment = 1";
//			statement.executeUpdate(sql);
//		} catch (SQLException e) {
//			System.out.println("Auto increment: " + e.getMessage());
//		}
		
	}
	
	public void newQuestion() {
		try {
			conn = DriverManager.getConnection(url, user, pass);
			String sql = "";
			PreparedStatement stmt = conn.prepareStatement(sql);
			sql = "use vaalikone";
			stmt.executeUpdate(sql);
			
			Random rand = new Random();
			ArrayList<Questionsvanha> questionsList = readAllQuestions();
			ArrayList<Candidates> candidatesList = readAllCandidates();
			int cand;
			int id = questionsList.size();
			
			ArrayList<Integer> candidateamount = new ArrayList<>();
			
			for (int i = 0; i < candidatesList.size(); i++) { // Etsitään suurin ID ehdokkaista
				candidateamount = readCandidateId();
			}

			System.out.println("CAND AMOUNT: " +candidateamount);
			int candmax = Collections.max(candidateamount);
			System.out.println("CAND MAX " + candmax);

			for (cand = 0; cand < candidateamount.size(); cand++) {

				try {
					int r = rand.nextInt(5) + 1;

					sql = "INSERT INTO answers (candidate_id, question_id, answer_int) VALUES (" + candidateamount.get(cand) + "," + id
							+ ", " + r + ");";
					stmt.executeUpdate(sql);

				} catch (SQLException e) {
					System.out.println("Candidate: " + cand + " Question: " + id + ": " + e.getMessage());

				}

			}
		} catch (SQLException e1) {
			System.out.println("insert question: " + e1.getMessage());
			
		}
	}
	
	public ArrayList<Candidates> deleteQuestion(String id) {
		try {
			conn = DriverManager.getConnection(url, user, pass);
			Statement stmt = conn.createStatement();
			String sql = "use vaalikone;";
			stmt.executeUpdate(sql);
			sql = "delete from answers where question_id=" + id;
			stmt.executeUpdate(sql);
			return readAllCandidates();
		} catch (SQLException e) {
			System.out.println("Deletoi kyssäri: " + e.getMessage());
			return null;
		}
	}

	public ArrayList<Candidates> deleteAnswer(String answer_id) {
		try {
			conn = DriverManager.getConnection(url, user, pass);
			Statement stmt = conn.createStatement();
			String sql = "use vaalikone;";
			stmt.executeUpdate(sql);
			sql = "delete from answers where answer_id=" + answer_id;
			stmt.executeUpdate(sql);
			return readAllCandidates();
		} catch (SQLException e) {
			System.out.println("Deletoi kyssäri: " + e.getMessage());
			return null;
		}
		
	}

}
