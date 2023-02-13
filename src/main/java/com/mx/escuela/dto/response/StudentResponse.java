package com.mx.escuela.dto.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudentResponse implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6155142712316007200L;
	private Integer idStudent;
	private String name;
	private String lastName;
	private String motherName;
	private Integer active;

}
