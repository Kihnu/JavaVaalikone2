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
import data.Candidates;

/**
 * Servlet implementation class DeleteCandidate
 */
@WebServlet("/DeleteCandidate")
public class DeleteCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Dao dao;

	@Override
	public void init() {
		String url = getServletContext().getInitParameter("connection_url_admin");
		String user = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("passwd");
		
		dao = new Dao(url, user, password);
	}
	
    public DeleteCandidate() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		ArrayList<Candidates> list=null;
		if (dao.getConnection()) {
			list=dao.deleteCandidate(id);
		}
		request.setAttribute("candidates", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/EditCandidates.jsp");
		rd.forward(request, response);
	}

}
