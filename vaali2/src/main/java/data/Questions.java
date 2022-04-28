package data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
//@NamedQuery(name="Questions_findAll", query="SELECT q FROM questions q")
public class Questions implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private String question_id;
	private String question;

	
	public Questions() {
	}

	
	public Questions(String question) {
		
		this.setQuestion(question);
	}

	public Questions(String question, String question_id) {
		
		this.setQuestion(question);
		this.question_id=question_id;
	}

	
	
	
	

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	public String getId() {
		return question_id;
	}
	public void setId(String id) {
		this.question_id = id;
	}
	
}