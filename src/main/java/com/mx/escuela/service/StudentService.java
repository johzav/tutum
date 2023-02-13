package com.mx.escuela.service;

import java.util.List;

import com.mx.escuela.dto.response.MatterResponse;
import com.mx.escuela.dto.response.StudentGradesResponse;
import com.mx.escuela.dto.response.StudentResponse;

public interface StudentService {

	List<StudentResponse> getAllStudents();

	StudentGradesResponse getStudentGrade(Integer idStudent);

	List<MatterResponse> getAllMatters();

	void addStudentGrade(Integer idStudent, Integer idMatter, Double grade);

	void updateStudentGrade(Integer idGrade, Integer idMatter, Double grade) throws Exception;

	void deleteStudentGrade(Integer idGrade);

}
