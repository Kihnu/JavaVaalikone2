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
import data.SingleCandidateAnswers;

/**
 * Servlet implementation class SingleCandidate
 */
@WebServlet("/SingleCandidate")
public class SingleCandidate extends HttpServlet {
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
    public SingleCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Ottaa napista inputin ja sen arvolla saa ehdokkaan numeron. Tällä tiedolla voi etsiä kandidaatin kysymykset
		String id=request.getParameter("id");
		ArrayList<SingleCandidateAnswers> singleCandidate = null;
		Candidates candidates=null;
	
		
		if (dao.getConnection()) {
			// daosta kandidaatille kysymykset
			// daosta tietyn kandidaatin vastaukset kysymyksiin
			singleCandidate = dao.readCertainCandidates(id);
			// System.out.println(id);
			candidates = dao.readCertainCandidate(id);
		} else {
			System.out.println("No connection to database");
		}
	
		request.setAttribute("singleCandidate", singleCandidate);
		//System.out.println(singleCandidate);
		request.setAttribute("candidate", candidates);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/SingleCandidateJSP.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
