package rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Questions;



/**
 * @author Toni, Erik & Janette
 *
 */

@Path("/questionservice")
public class QuestionService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaali2");

	
	
	
	/** Reads all the questions from the database.
	 * @return returns every question to a list.
	 */
	@GET
	@Path("/readquestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Questions> readquestion() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Questions> list = em.createQuery("select a from Questions a").getResultList();
		em.getTransaction().commit();
		return list;

	}

	
	/** Adds a question to the database.
	 * @return returns every question to a list.
	 */
	@POST
	@Path("/addquestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	public List<Questions> addquestion(Questions questions) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(questions);// The actual insertion line

		em.getTransaction().commit();

		List<Questions> list = readquestion();

		return list;
	}
	
	
	/** Updates question based on id.
	 * @return returns every question to a list.
	 */
	@PUT
	@Path("/updatequestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Questions> updatequestion(Questions questions) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Questions q = em.find(Questions.class, questions.getQuestion_id());

		if (q != null) {
			em.merge(questions);
		}
		em.getTransaction().commit();

		List<Questions> list = readquestion();
		return list;

	}

	
	/** Deletes question from database.
	 * @return returns every question to a list.
	 */
	@DELETE
	@Path("/deletequestion/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	// Request dispatcher t??nne ainakin
	public List<Questions> deletequestion(@PathParam("id") int question_id) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Questions q = em.find(Questions.class, question_id);

		if (q != null) {

			em.remove(q);

		}
		em.getTransaction().commit();

		List<Questions> list = readquestion();

		return list;
	}
	
	/** Reads one question based on id.
	 * @return returns every question to a list.
	 */

	@GET
	@Path("/readtoupdatequestion/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Questions readtoupdatequestion(@PathParam("id") int question_id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Questions q = em.find(Questions.class, question_id);
		em.getTransaction().commit();
		return q;

	}

}
