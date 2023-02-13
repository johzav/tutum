package com.mx.escuela.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.escuela.entity.StudentGradeEntity;

@Repository
public interface StudentGradeRepository extends JpaRepository<StudentGradeEntity, Integer> {

	List<StudentGradeEntity> findByIdStudent(Integer idStudent);

}
