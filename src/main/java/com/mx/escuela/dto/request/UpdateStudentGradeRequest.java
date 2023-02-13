package com.mx.escuela.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateStudentGradeRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7045869503440021019L;
	
	@NotNull
	private Integer idGrade;

	private Integer idMatter;

	@NotNull
	@Max(value = 10, message = "Calificacion maxima de 10")
	@Min(value = 0, message = "Calificacion minima de 0")
	private Double grade;
}
