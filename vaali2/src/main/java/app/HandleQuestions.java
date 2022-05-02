package app;

import java.io.*;
import java.util.*;

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

import DAO.Dao;
import data.Questions;

@WebServlet(urlPatterns = { "/addquestion", "/deletequestion", "/updatequestion", "/readquestion",
		"/readtoupdatequestion" })
public class HandleQuestions extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Dao dao;
	
	@Override
	public void init() {
		String url = getServletContext().getInitParameter("connection_url_admin");
		String user = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("passwd");
		
		dao = new Dao(url, user, password);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		List<Questions> list = null;

		switch (action) {
		case "/addquestion":
			list = addquestion(request);
			dao.newQuestion();
			break;

		case "/deletequestion":
			//String question_id = request.getParameter("question_id");
			dao.autoIncrement("answers");
			list = deletequestion(request);
			break;

		case "/updatequestion":
			list = updatequestion(request);
			break;

		case "/readquestion":
			list = readquestion(request);
			break;

		case "/readtoupdatequestion":
			Questions q = readtoupdatequestion(request);
			request.setAttribute("questions", q);
			RequestDispatcher rd = request.getRequestDispatcher("./jsp/UpdateQuestion.jsp");
			rd.forward(request, response);
			return;
		}
		
		dao.autoIncrement("questions");
		request.setAttribute("questionlist", list);
		RequestDispatcher rd = request.getRequestDispatcher("./jsp/Questionform.jsp");
		rd.forward(request, response);

	}

	private Questions readtoupdatequestion(HttpServletRequest request) {

		// LUETAAN 1 KYSYMYS

		String question_id = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/questionservice/readtoupdatequestion/" + question_id;
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		Questions questions = b.get(Questions.class);
		return questions;
	}

	private List<Questions> addquestion(HttpServletRequest request) {

		// LISÄTÄÄN KYSYMYS

		// A Fish object to send to our web-service
		Questions q = new Questions(request.getParameter("question"));
		String uri = "http://127.0.0.1:8080/rest/questionservice/addquestion";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		// Here we create an Entity of a Fish object as JSON string format
		Entity<Questions> e = Entity.entity(q, MediaType.APPLICATION_JSON);
		// Create a GenericType to be able to get List of objects
		// This will be the second parameter of post method
		GenericType<List<Questions>> genericList = new GenericType<List<Questions>>() {
		};

		// Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<Questions> returnedList = b.post(e, genericList);
		return returnedList;
	}

	// LUETAAN KYSYMYKSET

	private List<Questions> readquestion(HttpServletRequest request) {
		// String question_id = request.getParameter("question_id");
		String uri = "http://127.0.0.1:8080/rest/questionservice/readquestion";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		// Create a GenericType to be able to get List of objects
		// This will be the second parameter of post method
		GenericType<List<Questions>> genericList = new GenericType<List<Questions>>() {
		};

		List<Questions> returnedList = b.get(genericList);
		return returnedList;
	}

	// MUOKATAAN KYSYMYSTÄ

	private List<Questions> updatequestion(HttpServletRequest request) {
		// A Fish object to send to our web-service
		Questions q = new Questions(request.getParameter("id"), request.getParameter("question"));
		String uri = "http://127.0.0.1:8080/rest/questionservice/updatequestion";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		// Here we create an Entity of a Fish object as JSON string format
		Entity<Questions> e = Entity.entity(q, MediaType.APPLICATION_JSON);
		// Create a GenericType to be able to get List of objects
		// This will be the second parameter of post method
		GenericType<List<Questions>> genericList = new GenericType<List<Questions>>() {
		};

		// Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<Questions> returnedList = b.put(e, genericList);
		return returnedList;
	}

	// POISTETAAN KYSYMYS

	private List<Questions> deletequestion(HttpServletRequest request) {
		String question_id = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/questionservice/deletequestion/" + question_id;
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		// Create a GenericType to be able to get List of objects
		// This will be the second parameter of post method
		GenericType<List<Questions>> genericList = new GenericType<List<Questions>>() {
		};

		// Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<Questions> returnedList = b.delete(genericList);
		dao.deleteQuestion(question_id);
		return returnedList;
	}

}
