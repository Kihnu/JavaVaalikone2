package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Dao;
//import data.Candidates;
import data.Questionsvanha;

/**
 * Servlet implementation class Comparison
 */
@WebServlet("/Comparison")
public class Comparison extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao;
	private Questionsvanha questionsvanha;

	@Override
	public void init() {
		String url = getServletContext().getInitParameter("connection_url_admin");
		String user = getServletContext().getInitParameter("username");
		String password = getServletContext().getInitParameter("passwd");

		dao = new Dao(url, user, password);
		questionsvanha = new Questionsvanha();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Comparison() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int number;

		ArrayList<Questionsvanha> questionlist = null;
		//ArrayList<Candidates> candidates = null;
		ArrayList<Integer> candidateanswers = null;

		ArrayList<Integer> userlist = new ArrayList<>();
		ArrayList<Integer> candidatelist = null;

		ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();

		if (dao.getConnection()) {
			questionlist = dao.readAllQuestions();
			//candidates = dao.readAllCandidates();
		} else {
			System.out.println("No connection to database");
		}

		for (int i = 0; i < questionlist.size(); i++) { // Niin kauan kun kysymyksi� riitt��, katsotaan mit� ��nest�j�
														// on vastannut
			String answer = request.getParameter("answer" + (i + 1));
			// System.out.println("aanestaja: kysymys " + (i+1) + " - " + answer);
			if (answer.equals("option1")) {
				number = 1;
			} else if (answer.equals("option2")) {
				number = 2;
			} else if (answer.equals("option3")) { // Nappien arvon perusteella annetaan numeerinen vastaus
				number = 3;
			} else if (answer.equals("option4")) {
				number = 4;
			} else {
				number = 5;
			}
			userlist.add(questionsvanha.getId() + i, number);

		}

		ArrayList<Integer> candidateamount = new ArrayList<>();

		candidateamount = dao.readCandidateId(); // Etsitään suurin ID ehdokkaista

		int candmax = Collections.max(candidateamount);
		System.out.println("CAND MAX COMP " + candmax);

		for (int id = 1; id < candmax + 1; id++) { // Lis�t��n ehdokkaiden kaikki vastaukset omiin listoihinsa
			candidatelist = new ArrayList<>();
			candidateanswers = dao.readCertainAnswersC(id);
			for (int j = 0; j < candidateanswers.size(); j++) {
				candidatelist.add(questionsvanha.getId() + j, candidateanswers.get(j));
			}

			lists.add(candidatelist);

			System.out.println("Ehdokas " + id + " vastaukset: " + lists.get(lists.size() - 1));
		}

		for (int loop = 0; loop < lists.size(); loop++) { // Jos vastauksia on liian vähän, niin lista poistetaan
			if (lists.get(loop).size() < candidateanswers.size()) {
				System.out.println("POISTOON: " + lists.get(loop));
				lists.remove(loop);
				loop--;
			}
		}

		System.out.println("Kayttajan vastaukset: " + userlist);

		int user_num;
		int candid_num;
		int sum = 0;
		int y = 0;
		int z = 0;

		ArrayList<Integer> comparison = null;

		for (int x = 0; x < candidateamount.size(); x++) { // ��nest�j�n vastaukset verrataan ehdokkaiden vastauksiin
			comparison = new ArrayList<>();
			candidatelist = lists.get(x);
			for (y = 0; y < userlist.size(); y++) {
				user_num = userlist.get(y); // Otetaan k�ytt�j�n vastaus tiettyyn kysymykseen
				candid_num = candidatelist.get(y); // Otetaan tietyn ehdokkaan vastaus samaan kysymykseen
				int bigger = Math.max(user_num, candid_num); // Otetaan n�ist� kahdesta isompi
				int smaller = Math.min(user_num, candid_num); // Ja pienempi
				int diff = bigger - smaller; // Isommasta miinustetaan pienempi luku
				int percentage = diff * 25; // Kerrotaan erotus 25
				int comp = 100 - percentage; // Miinustetaan edellinen luku 100:sta
				comparison.add(comp); // Lis�t��n tulos listaan.
			}
			for (z = 0; z < comparison.size(); z++) { // Lis�t��n kaikki tulokset yhteen
				sum = sum + comparison.get(z);
			}

			int average = sum / comparison.size(); // Jaetaan kaikkien tuloksien summa verrattavien kohteiden m��r�ll�
			System.out.println("TAMA ON TULOSSA DATABASEE: " + candidateamount.get(x) + " - " + average + " %");
			dao.addComparison(candidateamount.get(x), average);
			sum = 0;
		}

		RequestDispatcher rd = request.getRequestDispatcher("Results");
		rd.forward(request, response);
		// ehdokkaiden vastaustietokanta

		// request.setAttribute("AnswersC", candidateanswers);
		doGet(request, response);
	}

}
