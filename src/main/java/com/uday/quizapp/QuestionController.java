package com.uday.quizapp;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@GetMapping("allQuestions")
	public String getAllQuestions() {
		return "Hi, These are your questions !!";
	}
}
