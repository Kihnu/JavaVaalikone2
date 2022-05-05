package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import data.AnswersC;
import data.Candidates;
import data.Questions;
import DAO.Dao;


@WebServlet(urlPatterns = {"addanswers", "/editanswers", "/readanswers", "/deleteanswers"})
public class HandleAnswers extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Dao dao;
	
	@Override
	public void init() {
		String url = getServletContext().getInitParameter("connection_url_admin");
		String user = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("passwd");
		
		dao = new Dao(url, user, password);
	}
       
	
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<AnswersC> list=updateanswers(request);
//		
//		
//		request.setAttribute("candidates", list);
//		RequestDispatcher rd=request.getRequestDispatcher("/jsp/EditCandidates.jsp");
//		rd.forward(request, response);
//		doGet(request, response);
//	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		List<AnswersC> list = null;

		switch (action) {
		case "/addanswer":
			list = addanswer(request);
			//dao.newQuestion();
			break;

		case "/deleteanswer":
			//String answer_id = request.getParameter("answer_id");
			dao.autoIncrement("answers");
			list = deleteanswer(request);
			break;

		case "/updateanswer":
			list = updateanswers(request);
			break;

		case "/readanswer":
			list = readanswers(request);
			break;

//		case "/readtoupdateanswer":
//			Questions q = readtoupdateanswer(request);
//			request.setAttribute("answers", a);
//			RequestDispatcher rd = request.getRequestDispatcher("./jsp/UpdateQuestion.jsp");
//			rd.forward(request, response);
//			return;
		}
		
		//dao.autoIncrement("questions");
		request.setAttribute("answerslist", list);
		RequestDispatcher rd = request.getRequestDispatcher("./jsp/CandidateAnswerForm.jsp");
		rd.forward(request, response);

	}
	
	private AnswersC readtoupdateanswers(HttpServletRequest request) {

		// LUETAAN 1 KYSYMYS

		String answer_id = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/questionservice/readtoupdateanswer/" + answer_id;
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		AnswersC answersc = b.get(AnswersC.class);
		return answersc;
	}
	
	//  Luetaan kysymykset
	
	private List<AnswersC> readanswers(HttpServletRequest request) {
		// String question_id = request.getParameter("question_id");
		String uri = "http://127.0.0.1:8080/rest/questionservice/readquestion";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		// Create a GenericType to be able to get List of objects
		// This will be the second parameter of post method
		GenericType<List<AnswersC>> genericList = new GenericType<List<AnswersC>>() {
		};

		List<AnswersC> returnedList = b.get(genericList);
		return returnedList;
	}
	
	private List<AnswersC> addanswer(HttpServletRequest request) {

		// LISÄTÄÄN KYSYMYS

		// A Fish object to send to our web-service
		//Questions q = new Questions(request.getParameter("question"));
		AnswersC q = new AnswersC(request.getParameter("answer_id"));  // Mitä laittais getParameter sisälle :/
		String uri = "http://127.0.0.1:8080/rest/questionservice/addanswer";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		// Here we create an Entity of a Fish object as JSON string format
		Entity<AnswersC> e = Entity.entity(q, MediaType.APPLICATION_JSON);
		// Create a GenericType to be able to get List of objects
		// This will be the second parameter of post method
		GenericType<List<AnswersC>> genericList = new GenericType<List<AnswersC>>() {
		};

		// Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<AnswersC> returnedList = b.post(e, genericList);
		return returnedList;
	}
	
	private List<AnswersC> updateanswers(HttpServletRequest request) {
		//A Fish object to send to our web-service 
		AnswersC a=new AnswersC(request.getParameter("answerId"), request.getParameter("candidateId"), request.getParameter("questionId"), request.getParameter("answerint"), request.getParameter("answerstring"));
		System.out.println(a);
		String uri = "http://127.0.0.1:8080/rest/answerservice/EditAnswers";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Fish object as JSON string format
		Entity<AnswersC> e=Entity.entity(a,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<AnswersC>> genericList = new GenericType<List<AnswersC>>() {};
		
		//Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<AnswersC> returnedList=b.put(e, genericList);
		return returnedList;
	}
	
	// POISTETAAN Vastaus

	private List<AnswersC> deleteanswer(HttpServletRequest request) {
		String answer_id = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/answerservice/answerquestion/" + answer_id;
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		// Create a GenericType to be able to get List of objects
		// This will be the second parameter of post method
		GenericType<List<AnswersC>> genericList = new GenericType<List<AnswersC>>() {
		};

		// Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<AnswersC> returnedList = b.delete(genericList);
		//dao.deleteQuestion(question_id);
		dao.deleteAnswer(answer_id);
		return returnedList;
	}

}
