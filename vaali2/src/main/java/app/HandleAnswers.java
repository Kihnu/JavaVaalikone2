package app;

import java.io.IOException;
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
//import data.Candidates;
//import data.Questions;
import DAO.Dao;


@WebServlet(urlPatterns = {"/updateanswer", "/readanswers", "/readtoupdateanswers"})
public class HandleAnswers extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private Dao dao;
	
	@Override
	public void init() {
		String url = getServletContext().getInitParameter("connection_url_admin");
		String user = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("passwd");
		
		dao = new Dao(url, user, password);
	}
       
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		List<AnswersC> list = null;

		switch (action) {
		case "/updateanswer":
			list = updateanswer(request);
			break;

		case "/readanswers":
			list = readanswers(request);
			break;

		case "/readtoupdateanswers":
			list = readtoupdateanswers(request);
			request.setAttribute("answerlist", list);
			RequestDispatcher rd = request.getRequestDispatcher("./jsp/CandidateAnswerForm.jsp");
			rd.forward(request, response);
			return;
		}
		
		//dao.autoIncrement("questions");
		request.setAttribute("answers", list);
		RequestDispatcher rd = request.getRequestDispatcher("/AdminCandidates");
		rd.forward(request, response);

	}
	
	private List<AnswersC> readtoupdateanswers(HttpServletRequest request) {

		// LUETAAN TIETYT VASTAUKSET
		String candid = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/answerservice/readtoupdateanswers/" + candid;
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		GenericType<List<AnswersC>> genericList = new GenericType<List<AnswersC>>() {
		};

		List<AnswersC> answers = b.get(genericList);
		
		//AnswersC answers = b.get(AnswersC.class);
		return answers;
	}
	
	//  Luetaan vastaukset
	
	private List<AnswersC> readanswers(HttpServletRequest request) {
		//String candId = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/answerservice/readanswers/";
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

	private List<AnswersC> updateanswer(HttpServletRequest request) {
		AnswersC a = new AnswersC(request.getParameter("answer_id"), request.getParameter("candidate_id"), request.getParameter("question_id"), request.getParameter("answer_int"), request.getParameter("answer"));
		String uri = "http://127.0.0.1:8080/rest/answerservice/updateanswer";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		Entity<AnswersC> e = Entity.entity(a,MediaType.APPLICATION_JSON);

		GenericType<List<AnswersC>> genericList = new GenericType<List<AnswersC>>() {};
		
		List<AnswersC> returnedList = b.put(e, genericList);
		return returnedList;

	}

}
