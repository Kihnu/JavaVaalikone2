package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CandidateAnswers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int candidateanswer_id;
	private String candidateanswer;
	
	public CandidateAnswers() {
	}
	
	public CandidateAnswers(String candidateanswer) {
		this.candidateanswer = candidateanswer; 
	}
	
	public CandidateAnswers(int candidateanswer_id, String candidateanswer) {
		this.candidateanswer_id = candidateanswer_id;
		this.candidateanswer = candidateanswer; 
	}
	
	public CandidateAnswers(String candidateanswer_id, String candidateanswer) {
		this.setCandidateAnswer_id(candidateanswer_id);
		this.candidateanswer = candidateanswer;
	}

	public int getCandidateAnswer_id() {
		return candidateanswer_id;
	}
	
	public void setCandidateAnswer_id(int candidateanswer_id) {
		this.candidateanswer_id = candidateanswer_id;
	}
	
	public void setCandidateAnswer_id(String candidateanswer_id) {
		try {
			this.candidateanswer_id = Integer.parseInt(candidateanswer_id);
		}
		catch (NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}

	public String getCandidateAnswer() {
		return candidateanswer;
	}
	

	public void setCandidateAnswer(String candidateanswer) {
		this.candidateanswer = candidateanswer;
	}

	public String toString() {
		return this.candidateanswer_id+": "+this.candidateanswer;
	}
}