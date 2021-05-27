package model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name = "readAllAnswers", query = "select an from Answer an")
public class Answer {
	
	@Id 
	@GeneratedValue
	private long id;
	private String answer;
	private boolean correct;
	
	public Answer(){
		
	}

	public Answer(String answer, boolean correct) {
		super();
		this.answer = answer;
		this.correct = correct;
	}
	
	public Answer(String answer) {
		super();
		this.answer = answer;
	}

	public long getId() {
		return id;
	}

	public String getAnswer() {
		return answer;
	}
	
	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answer=" + answer + ", correct=" + correct + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(answer, correct, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Answer)) {
			return false;
		}
		Answer other = (Answer) obj;
		return Objects.equals(answer, other.answer) && correct == other.correct && id == other.id;
	}
	
	

}
