package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Dao;
import data.Questions;

/**
 * Servlet implementation class AnsweringServlet
 */
@WebServlet("/AnsweringServlet")
public class AnsweringServlet extends HttpServlet {
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
    public AnsweringServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Questions> list = null;

		if (dao.getConnection()) {
			list = dao.readAllQuestions();
		} else {
			System.out.println("No connection to database");
		}
		
		request.setAttribute("questionlist", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/Answering.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		
		
	}

}
