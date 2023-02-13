package com.mx.escuela.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mx.escuela.dto.response.StudentGrades;
import com.mx.escuela.entity.StudentGradeEntity;

public class StudentGradesMapper {
	
	public static List<StudentGrades> formatGradesList(List<StudentGradeEntity> listGradesEntities) {
		List<StudentGrades> listGrades = new ArrayList<>();
		// format response
		for (StudentGradeEntity entity : listGradesEntities) {
			StudentGrades item = StudentGrades.builder()
					.id_t_usuario(entity.getIdStudent())
					.id_t_calificacion(entity.getIdCal())
					.id_t_materia(entity.getMatter().getIdMat())
					.nombre(entity.getStudent().getName())
					.apellido(entity.getStudent().getLastName().concat(entity.getStudent().getMotherName()) )
					.materia(entity.getMatter().getName())
					.calificacion(entity.getGrade())
					.fecha_registro(entity.getDateGrade())
					.build(); 
			listGrades.add(item);
		}
		return listGrades;
	}

}
