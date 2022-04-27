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
	private String id;
	private String question;

	
	public Questions() {
	}

	
	public Questions(String question) {
		
		this.setQuestion(question);
	}

	public Questions(String question, String string) {
		
		this.setQuestion(question);
		this.id=string;
	}

	
	
	
	

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}