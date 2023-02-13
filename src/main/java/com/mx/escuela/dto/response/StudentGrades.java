package com.mx.escuela.dto.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StudentGrades implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7577314008323768915L;
	private Integer id_t_usuario;
	private Integer id_t_calificacion;
	private Integer id_t_materia;
	private String nombre; 
	private String apellido;
	private String materia;
	private Double calificacion;
	
    @JsonFormat(pattern="dd/mm/yyyy")
	private Date fecha_registro;	

}
