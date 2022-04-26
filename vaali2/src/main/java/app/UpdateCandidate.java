package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Candidates;
import DAO.Dao;

/**
 * Servlet implementation class UpdateCandidate
 */
@WebServlet("/UpdateCandidate")
public class UpdateCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Dao dao;

	@Override
	public void init() {
		String url = getServletContext().getInitParameter("connection_url_admin");
		String user = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("passwd");
		
		dao = new Dao(url, user, password);
	}
    public UpdateCandidate() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String firstname=request.getParameter("firstname");
		String surname=request.getParameter("surname");
		String age=request.getParameter("age");
		String party=request.getParameter("party");
		String profession=request.getParameter("pro");
		String why=request.getParameter("why");
		String what=request.getParameter("what");
		String vote_nro=request.getParameter("vote");
		
		Candidates c=new Candidates(id, firstname, surname, party, age, profession, why, what, vote_nro);
				
		ArrayList<Candidates> list=null;
		if (dao.getConnection()) {
			list=dao.updateCandidate(c);
		}
		
		request.setAttribute("candidates", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/EditCandidates.jsp");
		rd.forward(request, response);
	}

}
