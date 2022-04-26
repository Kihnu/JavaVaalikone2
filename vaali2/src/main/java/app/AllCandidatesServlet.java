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
 * Servlet implementation class AllCandidatesServlet
 */
@WebServlet("/AllCandidatesServlet")
public class AllCandidatesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO.Dao dao=null;
	
	public void init() {
		String url = getServletContext().getInitParameter("connection_url");
		String user = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("passwd");
		
		dao = new Dao(url, user, password);
	}
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllCandidatesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		ArrayList<Candidates> list=null;
		if (dao.getConnection()){
			list=dao.readAllCandidates();
		}
		else {
			System.out.println("No connection to database");
		}
		request.setAttribute("Candidates", list);
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/AllCandidatesJSP.jsp");
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
