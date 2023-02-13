package com.mx.escuela.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_calificaciones")
public class StudentGradeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_calificaciones")
	private Integer idCal;
	
	@Column(name = "id_t_usuarios")
	private Integer idStudent;
	
	@Column(name = "id_t_materias")
	private Integer idMatter;
	
	@Column(name = "calificacion")
	private Double grade;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	private Date dateGrade;
	
	@ManyToOne
	@JoinColumn(name = "id_t_usuarios", 
			referencedColumnName= "id_t_usuarios", insertable = false, updatable = false)
    private StudentEntity student;
	
	@ManyToOne
	@JoinColumn(name = "id_t_materias", 
			referencedColumnName = "id_t_materias", insertable =  false, updatable = false)
    private MatterEntity matter;
}
