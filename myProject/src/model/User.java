package model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


@Entity
@NamedQuery(name="getAllUsers", query="select u from User u")
@NamedNativeQuery(name = "findUser" , query="select * from userTab WHERE userName = ? AND passWord = ?", resultClass = User.class)
@Table(name = "userTab")
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique = true, nullable = false)
	private String userName;
	@Column(nullable = false)
	private String passWord;

	
	@ManyToOne(fetch = FetchType.EAGER)
	private UserType type;
	

	public User() {

	}
	

	public User(String userName, String passWord, UserType type) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.type = type;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public long getId() {
		return id;
	}


	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", type=" + type + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, passWord, type, userName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return id == other.id && Objects.equals(passWord, other.passWord) && Objects.equals(type, other.type)
				&& Objects.equals(userName, other.userName);
	}


}
