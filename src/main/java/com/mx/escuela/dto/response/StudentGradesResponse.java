package com.mx.escuela.dto.response;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentGradesResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -202776283339706321L;
	private List<StudentGrades> calificaciones;	
	private Double promedio;
}
