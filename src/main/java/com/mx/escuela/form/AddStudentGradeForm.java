package com.mx.escuela.form;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddStudentGradeForm implements Serializable {

	private static final long serialVersionUID = 4566693259892136496L;

	@NotNull(message = "El estudiante es requerido")
	private Integer idStudent;

	@NotNull(message = "La materia es requerida")
	private Integer idMatter;

	@NotNull
	@Max(value = 10, message = "Calificacion maxima de 10")
	@Min(value = 0, message = "Calificacion minima de 0")
	private Double grade;
}
