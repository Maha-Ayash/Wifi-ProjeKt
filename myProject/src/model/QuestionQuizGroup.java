package model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;


@Entity
@NamedQuery(name = "readAllGroups", query = "select gr from QuestionQuizGroup gr")
public class QuestionQuizGroup {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique = true)
	private String questionQuizGroup;
	

	public QuestionQuizGroup() {
		
	}

	
	public String getQuestionQuizGroup() {
		return questionQuizGroup;
	}

	public void setQuestionQuizGroup(String questionQuizGroup) {
		this.questionQuizGroup = questionQuizGroup;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "" + questionQuizGroup + "";
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id, questionQuizGroup);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof QuestionQuizGroup)) {
			return false;
		}
		QuestionQuizGroup other = (QuestionQuizGroup) obj;
		return id == other.id && questionQuizGroup == other.questionQuizGroup;
	}

	
}
