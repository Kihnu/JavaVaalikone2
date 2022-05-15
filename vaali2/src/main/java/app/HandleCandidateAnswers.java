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
import data.CandidateAnswers;



/**
 * @author Toni, Erik & Janette
 *
 */

@WebServlet(urlPatterns = { "/addcandidateanswer", "/deletecandidateanswer", "/updatecandidateanswer", "/readcandidateanswer",
		"/readtoupdatecandidateanswer" })
public class HandleCandidateAnswers extends HttpServlet {

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
		List<CandidateAnswers> list = null;

		switch (action) {
		case "/addcandidateanswer":
			list = addCandidateAnswer(request);
			break;

		case "/deletecandidateanswer":
			//String question_id = request.getParameter("candidateanswer_id");
			list = deleteCandidateAnswer(request);
			break;

		case "/updatecandidateanswer":
			list = updateCandidateAnswer(request);
			break;

		case "/readcandidateanswer":
			list = readCandidateAnswer(request);
			break;

		case "/readtoupdatecandidateanswer":
			CandidateAnswers ca = readtoupdateCandidateAnswer(request);
			request.setAttribute("candidateanswers", ca);
			RequestDispatcher rd = request.getRequestDispatcher("./jsp/UpdateQuestion.jsp");
			rd.forward(request, response);
			return;
		}
		
		dao.autoIncrement("candidateanswers");
		request.setAttribute("candidateanswerlist", list);
		RequestDispatcher rd = request.getRequestDispatcher("./jsp/CandidateAnswerForm.jsp");
		rd.forward(request, response);

	}

	private CandidateAnswers readtoupdateCandidateAnswer(HttpServletRequest request) {

		// Luetaan 1 kandidaatti

		String candidateanswer_id = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/candidateanswerservice/readtoupdatecandidateanswer/" + candidateanswer_id;
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		CandidateAnswers candidateanswers = b.get(CandidateAnswers.class);
		return candidateanswers;
	}

	private List<CandidateAnswers> addCandidateAnswer(HttpServletRequest request) {

		// Lisätään kandidaatin vastaus

		// A CandidateAnswers object to send to our web-service
		CandidateAnswers ca = new CandidateAnswers(request.getParameter("candidateanswer"));
		String uri = "http://127.0.0.1:8080/rest/candidateanswerervice/addcandidateanswer";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		// Here we create an Entity of a CandidateAnswers object as JSON string format
		Entity<CandidateAnswers> e = Entity.entity(ca, MediaType.APPLICATION_JSON);
		// Create a GenericType to be able to get List of objects
		// This will be the second parameter of post method
		GenericType<List<CandidateAnswers>> genericList = new GenericType<List<CandidateAnswers>>() {
		};

		// Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<CandidateAnswers> returnedList = b.post(e, genericList);
		return returnedList;
	}

	// Luetaan kandidaatin vastaukset

	private List<CandidateAnswers> readCandidateAnswer(HttpServletRequest request) {
		// String candidateanswer_id = request.getParameter("candidateanswer_id");
		String uri = "http://127.0.0.1:8080/rest/candidateanwerservice/readcandidateanswer";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		// Create a GenericType to be able to get List of objects
		// This will be the second parameter of post method
		GenericType<List<CandidateAnswers>> genericList = new GenericType<List<CandidateAnswers>>() {
		};

		List<CandidateAnswers> returnedList = b.get(genericList);
		return returnedList;
	}

	// Päivitetään kandidaatin vastausta

	private List<CandidateAnswers> updateCandidateAnswer(HttpServletRequest request) {
		// A CandidateAnswer object to send to our web-service
		CandidateAnswers ca = new CandidateAnswers(request.getParameter("id"), request.getParameter("candidateanswer"));
		String uri = "http://127.0.0.1:8080/rest/candidateanswerservice/updatecandidateanswer";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		// Here we create an Entity of a CandidateAnswer object as JSON string format
		Entity<CandidateAnswers> e = Entity.entity(ca, MediaType.APPLICATION_JSON);
		// Create a GenericType to be able to get List of objects
		// This will be the second parameter of post method
		GenericType<List<CandidateAnswers>> genericList = new GenericType<List<CandidateAnswers>>() {
		};

		// Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<CandidateAnswers> returnedList = b.put(e, genericList);
		return returnedList;
	}

	// Poistetaan kandidaatin vastaus

	private List<CandidateAnswers> deleteCandidateAnswer(HttpServletRequest request) {
		String question_id = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/candidateanswerservice/deletecandidateanswer/" + question_id;
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		// Create a GenericType to be able to get List of objects
		// This will be the second parameter of post method
		GenericType<List<CandidateAnswers>> genericList = new GenericType<List<CandidateAnswers>>() {
		};

		// Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		List<CandidateAnswers> returnedList = b.delete(genericList);
		return returnedList;
	}

}
