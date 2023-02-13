package com.mx.escuela.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_alumnos")
public class StudentEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_usuarios")
	private Integer idStudent;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "ap_paterno")
	private String lastName;
	
	@Column(name = "ap_materno")
	private String motherName;
	
	@Column(name = "activo")
	private Integer active;

}
