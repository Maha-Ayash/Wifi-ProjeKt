package model;


import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;


@Entity
@NamedQuery(name = "readAllUserType", query = "select ty from UserType ty")
public class UserType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

//	@Column(unique = true)
//    private String type;
	
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
     private Type type;

	public UserType() {

	}
	

	public UserType(Type type) {
		super();
		this.type = type;
	}


	public long getId() {
		return id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "" + type + "";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserType)) {
			return false;
		}
		UserType other = (UserType) obj;
		return id == other.id && type == other.type;
	}


}
