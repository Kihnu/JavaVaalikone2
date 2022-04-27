package rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.AnswersC;

@Path("/answerservice")
public class AnswerService {
	
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaali2");
	@GET
	@Path("/readanswers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<AnswersC> readAnswers() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<AnswersC> list=em.createQuery("select xyx from answers xyx").getResultList();		
		em.getTransaction().commit();
		return list;
	}
	
	@PUT
	@Path("/EditAnswers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<AnswersC> updateAnswers(AnswersC candidateId) {
		//Dao.EditAnswers(answer);
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		AnswersC a=em.find(AnswersC.class, candidateId.getCandidateId());
		if (a!=null) {
			em.merge(candidateId);//The actual update line
		}
		em.getTransaction().commit();
		List<AnswersC> list=readAnswers();		
		return list;
		
	}
		
}
