package data;

public class SingleCandidateAnswers {

	private int answer_int;;
	private String question;
	
	public SingleCandidateAnswers(String answer_int, String question) {
		setAnswer_int(answer_int);
		this.question=question;
	}
	
	public SingleCandidateAnswers() {
	
	}
	
	public int getAnswer_int() {
		return answer_int;
	}
	public void setAnswer_int(int answer_int) {
		this.answer_int = answer_int;
	}
	public void setAnswer_int(String answer_int) {
		try {
			this.answer_int = Integer.parseInt(answer_int);
		}
		catch(NumberFormatException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	

}
