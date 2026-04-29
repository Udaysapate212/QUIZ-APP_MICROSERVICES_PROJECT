package com.uday.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uday.quizapp.dao.QuizDao;
import com.uday.quizapp.dao.QuestionDAO;
import com.uday.quizapp.model.Question;
import com.uday.quizapp.model.QuestionWrapper;
import com.uday.quizapp.model.Quiz;
import com.uday.quizapp.model.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDAO questionDao;
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		//We will need Optional here
		//Why?? Because there might chances that particular id not exits
		//So, the data is optional, if present we will get it, if not there no worries
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Question> questionsFromDB = quiz.get().getQuestions();
		
		//now we have to convert each question manually to question wrapper
		//question wrapper dont contains rightanswer, category, difficultylevel
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		
		for(Question q : questionsFromDB) {
		    QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
		    questionsForUser.add(qw);
		}

		
		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz=quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		
		int right=0;
		int i=0;
		for(Response response:responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
				right++;
			}
				
			i++;
		}
		
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
}
