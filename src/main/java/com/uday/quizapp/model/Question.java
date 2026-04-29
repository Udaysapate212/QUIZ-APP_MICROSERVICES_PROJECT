package com.uday.quizapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id") 
    private Integer id;

    @Column(name = "question_title")
    @JsonProperty("questionTitle")
    private String questionTitle;
  
    @JsonProperty("option1")
    private String option1;
    
    @JsonProperty("option2")
    private String option2;
    
    @JsonProperty("option3")
    private String option3;
    
    @JsonProperty("option4")
    private String option4;

    @Column(name = "right_answer")
    @JsonProperty("rightAnswer")
    private String rightAnswer;

    @Column(name = "difficultylevel")
    @JsonProperty("difficultyLevel")
    private String difficultyLevel;

    @JsonProperty("category")
    private String category;

    // Getters & Setters (since, we have used lombok, we don't need to create getters and setters for each attribute)
}