package rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.AnswersC;
//import data.Questions;


/**
 * @author Toni, Erik & Janette
 *
 */
@Path("/answerservice")
public class AnswerService {
	
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaali2");
	
	
	
	/** Reads all the answers from the database.
	 * @return returns every answer to a list.
	 */
	@GET
	@Path("/readanswers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<AnswersC> readAnswers() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<AnswersC> list=em.createQuery("select xyx from AnswersC xyx").getResultList();
		em.getTransaction().commit();
		return list;
	}
	
	
	/** Reads one answer based on id.
	 * @return returns every question to a list.
	 */
	
	@GET
	@Path("/readtoupdateanswers/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<AnswersC> readtoupdateanswers(@PathParam("id") int candidate_id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<AnswersC> list=em.createQuery("select a from AnswersC a where a.candidate_id=?1").setParameter(1, candidate_id).getResultList();
		//AnswersC a=em.find(AnswersC.class, candidate_id);
		em.getTransaction().commit();
		em.close();
		return list;
	}
	
	/** Updates one question based on id.
	 * @return returns every question to a list.
	 */
	@PUT
	@Path("/updateanswer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<AnswersC> updateAnswers(AnswersC candidate_id) {
		//Dao.EditAnswers(answer);
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		AnswersC a=em.find(AnswersC.class, candidate_id.getCandidate_id());
		if (a!=null) {
			em.merge(candidate_id);//The actual update line
		}
		em.getTransaction().commit();
		List<AnswersC> list=readAnswers();		
		return list;
		
	}
	
	
}
