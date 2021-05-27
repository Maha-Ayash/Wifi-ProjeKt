package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQuery(name = "readAllQuestions", query = "select qu from Question qu")
@NamedNativeQuery(name = "findQuestion" , query="select * from Question WHERE question = ?", resultClass = Question.class)
public class Question {

	@Id
	@GeneratedValue
	private long id;

	private String question;

	@OneToMany(targetEntity=Answer.class,cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Answer> answers = new ArrayList<>();

	@ManyToOne
	private QuestionQuizGroup questionQuizGroup;

	public Question() {

	}
	
    //When we get data from a Database
	public Question(String question, List<Answer> answers, QuestionQuizGroup questionQuizGroup) {
		super();
		this.question = question;
		this.answers = answers;
		this.questionQuizGroup = questionQuizGroup;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public QuestionQuizGroup getQuestionQuizGroup() {
		return questionQuizGroup;
	}

	public void setQuestionQuizGroup(QuestionQuizGroup questionQuizGroup) {
		this.questionQuizGroup = questionQuizGroup;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", answers=" + answers + ", questionQuizGroup="
				+ questionQuizGroup + "]" + "\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(answers, id, question, questionQuizGroup);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Question)) {
			return false;
		}
		Question other = (Question) obj;
		return Objects.equals(answers, other.answers) && id == other.id && Objects.equals(question, other.question)
				&& Objects.equals(questionQuizGroup, other.questionQuizGroup);
	}

}
