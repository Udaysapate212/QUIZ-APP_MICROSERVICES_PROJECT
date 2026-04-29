package com.uday.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uday.quizapp.dao.QuestionDAO;
import com.uday.quizapp.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDAO questionDao;
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST); //if something goes wrong return an empty array
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		
		questionDao.save(question);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public String deleteQuestion(int id) {
		questionDao.deleteById(id);
		return "Deleted !!";
	}
	
}
