package database;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Answer;
import model.Question;
import model.QuestionQuizGroup;
import model.Type;
import model.User;
import model.UserType;

public class UserDatabaseCreater {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectJPA");
		EntityManager em = emf.createEntityManager();// JPA Connection
		System.out.println("Connected");

		// Create the 2 userType(Admin - Uesr)
		UserType admin = new UserType();
		admin.setType(Type.ADMIN);
		UserType user = new UserType();
		user.setType(Type.USER);

		User user1 = new User();
		user1.setUserName("Maha");
		user1.setPassWord("1982");
		user1.setType(admin);

		User user2 = new User();
		user2.setUserName("Hosab");
		user2.setPassWord("1974");
		user2.setType(user);

		User user3 = new User();
		user3.setUserName("Hagop");
		user3.setPassWord("2016");
		user3.setType(user);

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		// INSERT USER
		em.persist(user1);
		System.out.println("first" + user1);
		em.persist(user2);
		System.out.println("secound" + user2);
		em.persist(user3);
		System.out.println("third" + user3);

		transaction.commit();
        //Create the Quiz Groups
		QuestionQuizGroup java = new QuestionQuizGroup();
		java.setQuestionQuizGroup("JAVA");
		QuestionQuizGroup oop = new QuestionQuizGroup();
		oop.setQuestionQuizGroup("OOP");
		QuestionQuizGroup swe = new QuestionQuizGroup();
		swe.setQuestionQuizGroup("SWE");
		
		Question question1 = new Question();
		question1.setQuestion("Welche 8 elementaren Typen gibt es in Java?");
		question1.setQuestionQuizGroup(java);
		question1.setAnswers(List.of(
				new Answer("byte , short , int (integer), long , float , double , char (character) und boolean", true),
				new Answer("byte , short , int (integer), long , float , double , string und boolean", false),
				new Answer("byte , binary , int (integer), long , float , double , char (character) und boolean",
						true)));
		Question question2 = new Question();
		question2.setQuestion("Was ist der Unterschied zwischen einer Klasse und einem Objekt?");
		question2.setQuestionQuizGroup(oop);
		question2.setAnswers(List.of(new Answer(
				"Die Klasse ist die Definition dessen was ein Objekt ausmacht. Dort sind alle Attribute/Properties definiert und alle Methoden.",
				true), new Answer("Ein Objekt kann nicht so oft wie möglich erzeugt werden", false),
				new Answer("Das Objekt ist eine Instanz einer Klasse.", true)));
		Question question3 = new Question();
		question3.setQuestion("Was ist UML?");
		question3.setQuestionQuizGroup(swe);
		question3.setAnswers(List.of(new Answer(
				"Die Unified Modeling Language (UML) ist eine Sprache zur Beschreibung von Softwaresystemen.", true),
				new Answer("UML gilt heute als Standard für Analyse und Design Objektorientierter Anwendungen.", true),
				new Answer(
						"Die Aufgabe der UML ist Spezifikation, Visualisierung und Dokumentation von Modellen für Softwaresysteme.",
						true)));
		Question question4 = new Question();
		question4.setQuestion("Was ist die JDBC?");
		question4.setQuestionQuizGroup(java);
		question4.setAnswers(List.of(new Answer(
				"Java Database Connectivity (JDBC) is an application programming interface (API) for the programming language Java, which defines how a client may access a database.",
				true),
				new Answer("It is a Java-based data access technology used for Java database connectivity.", true),
				new Answer(
						"It provides methods to query and update data in a database, and is oriented toward relational databases.",
						true)));
		Question question5 = new Question();
		question5.setQuestion("Beschreiben Sie die wichtigsten Tätigkeiten, die im Zuge eines Software-Projekts anfallen!");
		question5.setQuestionQuizGroup(swe);
		question5.setAnswers(List.of(new Answer(
				"Organisation, Aufwandsschätzung, Entwurf, Modularität, Testen, Dokumentation",
				true),
				new Answer("nur Dokumentation", false),
				new Answer(
						"nur Organisation, Aufwandsschätzung, Entwurf",
						false)));
		Question question6 = new Question();
		question6.setQuestion("Welche Tätigkeiten fasst man unter dem Begriff „Design“ zusammen?");
		question6.setQuestionQuizGroup(swe);
		question6.setAnswers(List.of(new Answer(
				"Es ist GUI ",
				false),
				new Answer("Schnittstellen Klassen, Geschäftslogik Klassen, Speicher und Entitätsklassen, Datenbankklassen", true),
				new Answer(
						"Datenbankklassen",
						false)));
		Question question7 = new Question();
		question7.setQuestion("Was sind die wesentlichen Unterscheide zwischen Java und einer herkömmlichen Programmiersprache?");
		question7.setQuestionQuizGroup(java);
		question7.setAnswers(List.of(
				new Answer("Es gibt keine Unterscheide", false),
				new Answer("Es gibt keine Herkömmliche Programmiersprache", true),
				new Answer("Java Bytecode, Java Virtual Machine, Objekt Orientiertes Programmieren",
						true)));
		Question question8 = new Question();
		question8.setQuestion("Wie werden Java Parameter übergeben?");
		question8.setQuestionQuizGroup(java);
		question8.setAnswers(List.of(
				new Answer("methodenName(Typ variableName)", true),
				new Answer("in Java werden Parameter grundsätzlich als Wert übergeben, d.h. die Methode erhält lediglich den Wert, den der Aufrufer bereitstellt.", true),
				new Answer("Variable_Typ Variable_name = einer Wert.",
						true)));
		Question question9 = new Question();
		question9.setQuestion("Wie können Objekte erzeugt werden?");
		question9.setQuestionQuizGroup(oop);
		question9.setAnswers(List.of(
				new Answer("Mit dem Keyword \"new\" werden Objekte erzeugt.", true),
				new Answer("sie werden mit Hilfe des == -Operator erzeugt.", false),
				new Answer("Mit dem Keyword \"this.\" werden Objekte erzeugt.",
						false)));
		Question question10 = new Question();
		question10.setQuestion("Was versteht man unter Polymorphismus?");
		question10.setQuestionQuizGroup(oop);
		question10.setAnswers(List.of(
				new Answer("Die Vererbung von Superklassen Funktionalität in Subklassen.", false),
				new Answer("Es ist ein Konzept in der objektorientierten Programmierung, das ermöglicht, dass ein Bezeichner abhängig von seiner Verwendung Objekte unterschiedlichen Datentyps annimmt.", true),
				new Answer("inheritance.",
						false)));
		
		transaction.begin();
		// INSERT Question
		em.persist(question1);
		System.out.println(question1);
		em.persist(question2);
		System.out.println(question2);
		em.persist(question3);
		System.out.println(question3);
		em.persist(question4);
		System.out.println(question4);
		em.persist(question5);
		System.out.println(question5);
		em.persist(question6);
		System.out.println(question6);
		em.persist(question7);
		System.out.println(question7);
		em.persist(question8);
		System.out.println(question8);
		em.persist(question9);
		System.out.println(question9);
		em.persist(question10);
		System.out.println(question10);
		transaction.commit();

		em.close();
		emf.close();
		
	}
}
