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

import data.AnswersC;
import data.CandidateAnswers;

@Path("/candidateanswerservice")
public class CandidateAnswerService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaali2");

	@GET
	@Path("/readcandidateanswer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<AnswersC> readcandidateanswer() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<AnswersC> list=em.createQuery("select xyx from answers xyx").getResultList();		
		em.getTransaction().commit();
		return list;

	}

	@POST
	@Path("/addcandidateanswer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	// CandidateAnswers
	public List<AnswersC> addcandidateanswer(CandidateAnswers candidateanswers) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(candidateanswers);// The actual insertion line

		em.getTransaction().commit();

		List<AnswersC> list = readcandidateanswer();

		return list;
	}

	@PUT
	@Path("/updatecandidateanswer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<AnswersC> updateanswer(CandidateAnswers candidateanswers) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		CandidateAnswers q = em.find(CandidateAnswers.class, candidateanswers.getId());

		if (q != null) {
			em.merge(candidateanswers);
		}
		em.getTransaction().commit();

		List<AnswersC> list = readcandidateanswer();
		return list;

	}

	@DELETE
	@Path("/deletecandidateanswer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	public List<AnswersC> deleteanswer(@PathParam("id") int candidateanswer_id) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		CandidateAnswers q = em.find(CandidateAnswers.class, candidateanswer_id);

		if (q != null) {

			em.remove(q);

		}
		em.getTransaction().commit();

		List<AnswersC> list = readcandidateanswer();

		return list;
	}

	@GET
	@Path("/readtoupdatecandidateanswer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CandidateAnswers readtoupdateanswer(@PathParam("id") int candidateanswer_id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		CandidateAnswers q = em.find(CandidateAnswers.class, candidateanswer_id);
		em.getTransaction().commit();
		return q;

	}

}
