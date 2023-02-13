package com.mx.escuela.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mx.escuela.dto.response.MatterResponse;
import com.mx.escuela.dto.response.StudentGradesResponse;
import com.mx.escuela.dto.response.StudentResponse;
import com.mx.escuela.form.AddStudentGradeForm;
import com.mx.escuela.form.EditStudentGradeForm;
import com.mx.escuela.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController implements WebMvcConfigurer {

	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public String home(Model model) {
		List<StudentResponse> listStudents = studentService.getAllStudents();
		model.addAttribute("students", listStudents);
		log.info("Model: {}", model);
		return "students";
	}

	@GetMapping("student/{idStudent}/grades")
	public String showStudentGrades(Model model, @PathVariable Integer idStudent) {
		log.info("showStudentGrades, idStudent: {} ...", idStudent);

		StudentGradesResponse studentGrades = studentService.getStudentGrade(idStudent);

		model.addAttribute("grades", studentGrades.getCalificaciones());
		model.addAttribute("average", studentGrades.getPromedio());
		model.addAttribute("idStudent", idStudent);
		return "student_grades";
	}

	@GetMapping("/add_student_grade/idstudent/{idStudent}")
	public String showAddStudentGrade(Model model, AddStudentGradeForm addStudentGradeForm,
			@PathVariable Integer idStudent) {

		List<MatterResponse> mattersList = studentService.getAllMatters();
		model.addAttribute("mattersList", mattersList);
		model.addAttribute("idStudent", idStudent);

		return "add_student_grade";
	}

	@PostMapping("/addStudentGrade")
	public String addStudentGrade(Model model, @Valid AddStudentGradeForm addStudentGradeForm,
			BindingResult bindingResult) {

		log.debug("data addStudentGrade: {} ", addStudentGradeForm.toString());

		if (bindingResult.hasErrors()) {
			log.debug("! add grade error on form validations {}", bindingResult.getFieldError());
			return this.showAddStudentGrade(model, addStudentGradeForm, addStudentGradeForm.getIdStudent());
		}

		studentService.addStudentGrade(addStudentGradeForm.getIdStudent(), addStudentGradeForm.getIdMatter(),
				addStudentGradeForm.getGrade());

		return "redirect:/student/" + addStudentGradeForm.getIdStudent() + "/grades";
	}

	@GetMapping("/edit_student_grade")
	public String showEditStudentGrade(Model model, EditStudentGradeForm editStudentGradeForm,
			@RequestParam(required = true) Integer idStudent, @RequestParam(required = true) Integer idGrade,
			@RequestParam(required = true) Integer idMatter, @RequestParam(required = true) Double grade) {

		List<MatterResponse> mattersList = studentService.getAllMatters();
		model.addAttribute("mattersList", mattersList);
		model.addAttribute("idStudent", idStudent);
		model.addAttribute("idGrade", idGrade);
		model.addAttribute("idMatter", idMatter);
		model.addAttribute("grade", grade);
		model.addAttribute("mattersList", mattersList);

		return "edit_student_grade";
	}

	@PostMapping("/editStudentGrade")
	public String editStudentGrade(Model model, @Valid EditStudentGradeForm editStudentGradeForm,
			BindingResult bindingResult) throws Exception {

		log.debug("data addStudentGrade: {} ", editStudentGradeForm.toString());

		if (bindingResult.hasErrors()) {
			log.debug("! add grade error on edit form validations {}", bindingResult.getFieldError());
			return this.showEditStudentGrade(model, editStudentGradeForm, editStudentGradeForm.getIdStudent(),
					editStudentGradeForm.getIdGrade(), editStudentGradeForm.getIdMatter(),
					editStudentGradeForm.getGrade());
		}

		studentService.updateStudentGrade(editStudentGradeForm.getIdGrade(), editStudentGradeForm.getIdMatter(),
				editStudentGradeForm.getGrade());

		return "redirect:/student/" + editStudentGradeForm.getIdStudent() + "/grades";
	}

	@PostMapping("/deleteStudentGrade/{idGrade}/idStudent/{idStudent}")
	public String deleteStudentGrade(@PathVariable Integer idGrade, @PathVariable Integer idStudent) {
		log.info("deleting idGrade: {}", idGrade);
		studentService.deleteStudentGrade(idGrade);
		return "redirect:/student/" + idStudent + "/grades";

	}

}
