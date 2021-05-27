package test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.Answer;

class QuestionTest {

	@Test
	void testSetAnswer() {
		Answer answerUnterTest = new Answer();
		answerUnterTest.setAnswer("Hallo Test?");
		assertEquals(answerUnterTest.getAnswer(), "Hallo Test?");
		
	}

}
