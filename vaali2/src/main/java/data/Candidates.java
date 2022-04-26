package data;

public class Candidates {
	private int id;
	private String firstname;
	private String surname;
	private String party;
	private int age;
	private String profession;
	private String why;
	private String what;
	private int vote_nro;
	
	public Candidates(String id, String firstname, String surname, String party, String age, String profession, String why, String what, String vote_nro) {
		setId(id);
		this.firstname=firstname;
		this.surname=surname;
		this.party=party;
		setAge(age);
		this.profession=profession;
		this.why=why;
		this.what=what;
		setVote_nro(vote_nro);	
	}
	public Candidates() {
	
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
			System.out.println(e.getMessage());
		}
	}
	public String getfirstname() {
		return firstname;
	}
	public void setfirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getsurname() {
		return surname;
	}
	public void setsurname(String surname) {
		this.surname = surname;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setAge(String age) {
		try {
			this.age = Integer.parseInt(age);
		}
		catch(NumberFormatException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	public String getWhy() {
		return why;
	}
	public void setWhy(String why) {
		this.why = why;
	}
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	public int getVote_nro() {
		return vote_nro;
	}
	public void setVote_nro(int vote_nro) {
		this.vote_nro = vote_nro;
	}
	public void setVote_nro(String vote_nro) {
		try {
			this.vote_nro = Integer.parseInt(vote_nro);
		}
		catch(NumberFormatException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	
}