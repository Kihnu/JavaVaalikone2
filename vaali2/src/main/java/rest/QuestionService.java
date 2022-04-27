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



@Path("/questionservice")
	public class QuestionService {

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaali2");
	
	@GET
	@Path("/readquestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	
	public List<Questions> readquestion() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Questions> list=em.createQuery("select xyx from Question xyx").getResultList();		
		em.getTransaction().commit();
		return list;
	}	
	
	
	@POST
	@Path("/addquestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Questions> addquestion(Questions questions) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(questions);//The actual insertion line
		em.getTransaction().commit();
		//Calling the method readFish() of this service
		List<Questions> list=readquestion();		
		return list;
	}	
	
	
	@PUT
	@Path("/updatequestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Questions> updatequestion(Questions questions) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Questions q=em.find(Questions.class, questions.getId());
		if (q!=null) {
			em.merge(questions);//The actual update line
		}
		em.getTransaction().commit();
		//Calling the method readFish() of this service
		List<Questions> list=readquestion();		
		return list;
	}	
	
	@DELETE
	@Path("/deleteupdate/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Questions> deleteFish(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Questions q=em.find(Questions.class, id);
		if (q!=null) {
			em.remove(q);//The actual insertion line
		}
		em.getTransaction().commit();
		//Calling the method readFish() of this service
		List<Questions> list=readquestion();		
		return list;
	}	
	@GET
	@Path("/readtoupdatequestion/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Questions readToUpdatequestion(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Questions q=em.find(Questions.class, id);
		em.getTransaction().commit();
		return q;
	}	
	
	
	
	
	
	
}
