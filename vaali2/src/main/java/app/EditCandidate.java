package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Dao;
import data.Candidates;

/**
 * Servlet implementation class EditCandidate
 */
@WebServlet("/EditCandidate")
public class EditCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Dao dao;

	@Override
	public void init() {
		String url = getServletContext().getInitParameter("connection_url_admin");
		String user = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("passwd");
		
		dao = new Dao(url, user, password);
	}

	public EditCandidate() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		Candidates candidate = null;

		if (dao.getConnection()) {
			candidate = dao.readCertainCandidate(id);
		} else {
			System.out.println("No connection to database");
		}
	
		request.setAttribute("candidate", candidate);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/EditSingleCandidate.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}
}
