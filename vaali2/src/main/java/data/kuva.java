package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class kuva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
}
