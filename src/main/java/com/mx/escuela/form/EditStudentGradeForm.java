package com.mx.escuela.form;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EditStudentGradeForm extends AddStudentGradeForm {

	private static final long serialVersionUID = -4538140397822323266L;

	@NotNull(message = "La materia es requerida")
	private Integer idGrade;

}
