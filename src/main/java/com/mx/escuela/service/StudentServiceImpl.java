package com.mx.escuela.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.escuela.dto.response.MatterResponse;
import com.mx.escuela.dto.response.StudentGrades;
import com.mx.escuela.dto.response.StudentGradesResponse;
import com.mx.escuela.dto.response.StudentResponse;
import com.mx.escuela.entity.MatterEntity;
import com.mx.escuela.entity.StudentEntity;
import com.mx.escuela.entity.StudentGradeEntity;
import com.mx.escuela.mapper.StudentGradesMapper;
import com.mx.escuela.repository.MatterRepository;
import com.mx.escuela.repository.StudentGradeRepository;
import com.mx.escuela.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentGradeRepository studentGradeRepository;

	@Autowired
	private MatterRepository matterRepository;

	@Override
	public List<StudentResponse> getAllStudents() {
		List<StudentEntity> listStudents = studentRepository.findAll();
		List<StudentResponse> response = new ArrayList<>();
		for (StudentEntity student : listStudents) {
			StudentResponse studentDto = new StudentResponse();
			try {
				BeanUtils.copyProperties(studentDto, student);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			response.add(studentDto);
		}
		log.debug("getAllStudents {}", response);
		return response;
	}

	public StudentGradesResponse getStudentGrade(Integer idStudent) {
		List<StudentGradeEntity> listStudentGrades = studentGradeRepository.findByIdStudent(idStudent);
		log.info("Lista: {}", listStudentGrades.toString());
		List<StudentGrades> gradeList = StudentGradesMapper.formatGradesList(listStudentGrades);

		Double average = listStudentGrades.stream().collect(Collectors.averagingDouble(StudentGradeEntity::getGrade));
		StudentGradesResponse response = StudentGradesResponse.builder().calificaciones(gradeList)
				.promedio(average).build();
		return response;
	}

	@Override
	public List<MatterResponse> getAllMatters() {
		List<MatterEntity> listMatter = matterRepository.findAll();
		List<MatterResponse> response = new ArrayList<>();
		for (MatterEntity matter : listMatter) {
			MatterResponse matterDto = new MatterResponse();
			try {
				BeanUtils.copyProperties(matterDto, matter);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			response.add(matterDto);
		}
		return response;
	}

	@Override
	public void addStudentGrade(Integer idStudent, Integer idMatter, Double grade) {
		StudentGradeEntity studentGrade = StudentGradeEntity.builder().idStudent(idStudent).idMatter(idMatter)
				.grade(grade).build();
		studentGradeRepository.saveAndFlush(studentGrade);
	}

	@Override
	public void updateStudentGrade(Integer idGrade, Integer idMatter, Double grade) throws Exception {
		StudentGradeEntity studentGrade = studentGradeRepository.getReferenceById(idGrade);

		if (studentGrade == null) {
			log.error("El id del estudiante {} no fue encontrado...", idGrade);
			throw new Exception("not found to delete! idStudentGrade:" + idGrade);
		}
		if (idMatter != null) {
			studentGrade.setIdMatter(idMatter);
		}
		studentGrade.setGrade(grade);
		studentGrade.setDateGrade(new Date());
		studentGradeRepository.saveAndFlush(studentGrade);
	}

	@Override
	public void deleteStudentGrade(Integer idGrade) {
		studentGradeRepository.deleteById(idGrade);
	}

}
