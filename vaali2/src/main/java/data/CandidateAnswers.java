package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CandidateAnswers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int question_id;
	private String question;
	
	public CandidateAnswers() {
	}
	
	public CandidateAnswers(String question) {
		this.question = question; 
	}
	
	public CandidateAnswers(int question_id, String question) {
		this.question_id = question_id;
		this.question = question; 
	}
	
	public CandidateAnswers(String question_id, String question) {
		this.setCandidateAnswer_id(question_id);
		this.question = question;
	}

	public int getCandidateAnswer_id() {
		return question_id;
	}
	
	public void setCandidateAnswer_id(int question_id) {
		this.question_id = question_id;
	}
	
	public void setCandidateAnswer_id(String question_id) {
		try {
			this.question_id = Integer.parseInt(question_id);
		}
		catch (NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}

	public String getCandidateAnswer() {
		return question;
	}
	

	public void setCandidateAnswer(String question) {
		this.question = question;
	}

	public String toString() {
		return this.question_id+": "+this.question;
	}
}