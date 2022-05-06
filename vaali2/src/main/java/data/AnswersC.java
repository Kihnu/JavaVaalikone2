package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "answers")
public class AnswersC {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int answer_id;
	private int candidate_id;
	private int question_id;
	private int answer_int;
	private String answer_string;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="question_id")
	private Questions questions;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="candidate_id")
	private Candidates candidates;

	public AnswersC(String answer_id, int candidate_id, int question_id, int answer_int, String answer_string) {
		setanswer_id(answer_id);
		setCandidate_id(candidate_id);
		setQuestion_id(question_id);
		setanswer_int(answer_int);
		this.answer_string = answer_string;
	}

	public AnswersC() {
	}

	public AnswersC(String answer_id) {
		this.setanswer_id(answer_id);
	}

	public AnswersC(String answer_id, String candidate_id, String question_id, String answer_int,
			String answer_string) {
		this.setanswer_id(answer_id);
		this.setCandidate_id(candidate_id);
		this.setQuestion_id(question_id);
		this.setanswer_int(answer_int);
		this.answer_string = answer_string;
	}

	public int getanswer_id() {
		return answer_id;
	}

	public void setanswer_id(int answer_id) {
		this.answer_id = answer_id;
	}

	private void setanswer_id(String answer_id) {

		try {
			this.answer_id = Integer.parseInt(answer_id);
		} catch (NumberFormatException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

	}

	public int getCandidate_id() {
		return candidate_id;
	}

	public void setCandidate_id(int candidate_id) {
		this.candidate_id = candidate_id;
	}

	public void setCandidate_id(String candidate_id) {
		try {
			this.candidate_id = Integer.parseInt(candidate_id);
		} catch (NumberFormatException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public void setQuestion_id(String question_id) {
		try {
			this.question_id = Integer.parseInt(question_id);
		} catch (NumberFormatException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

//	public int answerint() {
//		return answerint;
//	}

	public int getanswer_int() {
		return answer_int;
	}

	public void setanswer_int(int answer_int) {
		this.answer_int = answer_int;
	}

	public void setanswer_int(String answer_int) {
		try {
			this.answer_int = Integer.parseInt(answer_int);
		} catch (NumberFormatException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getanswer_string() {
		return answer_string;
	}

	public void setanswer_string(String answer_string) {
		this.answer_string = answer_string;

	}

	public Questions getQuestions() {
		return this.questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	public Candidates getCandidates() {
		return this.candidates;
	}

	public void setCandidates(Candidates candidates) {
		this.candidates = candidates;
	}
	
	

}
