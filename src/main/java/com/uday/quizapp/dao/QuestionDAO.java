package com.uday.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uday.quizapp.model.Question;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer>{
	
	//JpaRepository asks for a 2 things
	//Class with which you are working, type of primary key
	
	List<Question>findByCategory(String category);
	
	@Query(value="SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery=true)
	List<Question> findRandomQuestionsByCategory(String category, int numQ);
	
}
