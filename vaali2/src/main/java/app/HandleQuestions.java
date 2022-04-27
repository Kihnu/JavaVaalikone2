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


import data.Question;


/**
 * Servlet implementation class HandleQuestions
 */
@WebServlet(urlPatterns = {"/addquestion", "/deletequestion","/updatequestion","/readquestion","/readtoupdatequestion"})
public class HandleQuestions extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	
	throws ServletException, IOException {
	doGet(request, response);
		
	}
    

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String action = request.getServletPath();
		  List<Question> list=null;
		  switch (action) {
		  case "/addquestion":
			  list=addquestion(request);break;
		  case "/deletequestion":
			  String id=request.getParameter("id");
			  list=deletequestion(request);break;
		  case "/updatequestion":
			  list=updatequestion(request);break;
		  case "/readquestion":
			  list=readquestion(request);break;
		  case "/readtoupdatequestion":
			  Question q=readtoupdatequestion(request);
			  request.setAttribute("questionlist", q);
			  RequestDispatcher rd=request.getRequestDispatcher("./jsp/questionupdate.jsp");
			  rd.forward(request, response);
			  return;
		  }
		  request.setAttribute("question", list);
		  RequestDispatcher rd=request.getRequestDispatcher("./jsp/Questionform.jsp");
		  rd.forward(request, response);
		  
	}

		
		
		
		private Question readtoupdatequestion(HttpServletRequest request) {
			
			
			//LUETAAN 1 KYSYMYS
			
			String id=request.getParameter("id");
			String uri = "http://127.0.0.1:8080/rest/questionservice/readtoupdatequestion/"+id;
			Client c=ClientBuilder.newClient();
			WebTarget wt=c.target(uri);
			Builder b=wt.request();
			Question question=b.get(Question.class);
			return question;
		}
		
		
		
		private List<Question> addquestion(HttpServletRequest request) {
			
			
			
			
			//LISÄTÄÄN KYSYMYS
			
			//A Fish object to send to our web-service 
			Question q=new Question(request.getParameter("question"));
			System.out.println(q);
			String uri = "http://127.0.0.1:8080/rest/questionservice/addquestion";
			Client c=ClientBuilder.newClient();
			WebTarget wt=c.target(uri);
			Builder b=wt.request();
			//Here we create an Entity of a Fish object as JSON string format
			Entity<Question> e=Entity.entity(q,MediaType.APPLICATION_JSON);
			//Create a GenericType to be able to get List of objects
			//This will be the second parameter of post method
			GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
			
			//Posting data (Entity<ArrayList<DogBreed>> e) to the given address
			List<Question> returnedList=b.post(e, genericList);
			return returnedList;
		}
		
		
		//LUETAAN KYSYMYKSET
		
		private List<Question> readquestion(HttpServletRequest request) {
			String id=request.getParameter("id");
			String uri = "http://127.0.0.1:8080/rest/questionservice/readquestion";
			Client c=ClientBuilder.newClient();
			WebTarget wt=c.target(uri);
			Builder b=wt.request();
			//Create a GenericType to be able to get List of objects
			//This will be the second parameter of post method
			GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
			
			List<Question> returnedList=b.get(genericList);
			return returnedList;
		}
		
		
		
		//MUOKATAAN KYSYMYSTÄ
		
		private List<Question> updatequestion(HttpServletRequest request) {
			//A Fish object to send to our web-service 
			Question q=new Question(request.getParameter("id"), request.getParameter("question"));
			System.out.println(q);
			String uri = "http://127.0.0.1:8080/rest/questionservice/updatequestion";
			Client c=ClientBuilder.newClient();
			WebTarget wt=c.target(uri);
			Builder b=wt.request();
			//Here we create an Entity of a Fish object as JSON string format
			Entity<Question> e=Entity.entity(q,MediaType.APPLICATION_JSON);
			//Create a GenericType to be able to get List of objects
			//This will be the second parameter of post method
			GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
			
			//Posting data (Entity<ArrayList<DogBreed>> e) to the given address
			List<Question> returnedList=b.put(e, genericList);
			return returnedList;
		}
		
		
		
		
		
		//POISTETAAN KYSYMYS
		
		private List<Question> deletequestion(HttpServletRequest request) {
			String id=request.getParameter("id");
			String uri = "http://127.0.0.1:8080/rest/questionservice/deletequestion/"+id;
			Client c=ClientBuilder.newClient();
			WebTarget wt=c.target(uri);
			Builder b=wt.request();
			//Create a GenericType to be able to get List of objects
			//This will be the second parameter of post method
			GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
			
			//Posting data (Entity<ArrayList<DogBreed>> e) to the given address
			List<Question> returnedList=b.delete(genericList);
			return returnedList;
		}
		
		
		

	




	 

}
