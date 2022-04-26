package data;

// M‰‰ritt‰‰ comparison tietokannan tablen arvoille getterit ja setterit
public class Comparison {
	private int id;
	private int comparisonID;
	private int comparisonPercent;
	private String firstname;
	private String lastname;
	private int vote;
	private String party;
	private int c_id;
	
	public int getComparisonID() {
		return comparisonID;
	}

	public void setComparisonID(int comparisonID) {
		this.comparisonID = comparisonID;
	}

	public int getComparisonPercent() {
		return comparisonPercent;
	}

	public void setComparisonPercent(int comparisonPercent) {
		this.comparisonPercent = comparisonPercent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}



}
