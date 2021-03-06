package com.example.demo.app.survey;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SurveyForm {
	@Max(150)
	@Min(0)
	private int age;

	@Max(5)
	@Min(1)
	private int satisfaction;

	@NotNull
	@Size(min=1,max=200)
	private String comment;
}
