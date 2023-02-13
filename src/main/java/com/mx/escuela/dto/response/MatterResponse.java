package com.mx.escuela.dto.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class MatterResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idMat;
	private String name;
	private Integer active;
}
