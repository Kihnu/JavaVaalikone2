package data;

public class Questionsvanha {
	private int id;
	private String question;
	public Questionsvanha(String id, String question) {
		setId(id);
		this.question=question;
	}
	public Questionsvanha() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value of id won't be changed
		}
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}