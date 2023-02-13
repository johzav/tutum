package com.mx.escuela.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.escuela.dto.request.AddStudentGradeRequest;
import com.mx.escuela.dto.request.UpdateStudentGradeRequest;
import com.mx.escuela.dto.response.StudentGradesResponse;
import com.mx.escuela.dto.response.StudentResponse;
import com.mx.escuela.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping(value = "api/grades", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
public class GradesController {

	@Autowired
	private StudentService studentService;

	/**
	 * Post student grade
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<String> addStudenGrade(@Valid @RequestBody AddStudentGradeRequest request) {
		log.debug("Add grade by idStudent: {}");
		studentService.addStudentGrade(request.getIdStudentGrade(), request.getIdMatter(), request.getGrade());
		return ResponseEntity.status(HttpStatus.OK).body("calificación registrada");
	}

	/**
	 * Put Student grade
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PutMapping()
	public ResponseEntity<String> updateGrades(@Valid @RequestBody UpdateStudentGradeRequest request) throws Exception {
		log.debug("Update student grade by id: {}", request);
		studentService.updateStudentGrade(request.getIdGrade(), request.getIdMatter(), request.getGrade());
		return ResponseEntity.status(HttpStatus.OK).body("calificación actualizada!");
	}

	/**
	 * Get Student grades by idStudent
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("student/{id}")
	public ResponseEntity<StudentGradesResponse> getGrades(@PathVariable Integer id) {
		log.debug("consulting grade from idStudent: {}...", id);
		StudentGradesResponse response = studentService.getStudentGrade(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * Get All students
	 * 
	 * @return
	 */
	@GetMapping("students")
	public ResponseEntity<List<StudentResponse>> getStudents() {
		log.debug("consulting all students");
		List<StudentResponse> response = studentService.getAllStudents();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * Delete student grade
	 * 
	 * @return
	 */
	@DeleteMapping("grade/{id}")
	public ResponseEntity<String> deleteGrades() {
		log.debug("Delete grade by id: {}");
		return ResponseEntity.status(HttpStatus.OK).body("calificación eliminada!");
	}

}
