package com.uday.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uday.quizapp.dao.QuestionDAO;
import com.uday.quizapp.model.Question;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDAO questionDao;
	
	public List<Question> getAllQuestions() {
		return questionDao.findAll();
	}
	
}
