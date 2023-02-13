package com.mx.escuela.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mx.escuela.dto.response.StudentGradesResponse;
import com.mx.escuela.service.StudentService;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Slf4j
@RestController
public class ReportController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/report/students/{idStudent}")
	public ResponseEntity<byte[]> getEmployeeRecordReport(@PathVariable Integer idStudent) {

		try {
			StudentGradesResponse studentsGradeList = studentService.getStudentGrade(idStudent);

			// dynamic parameters required for report
			Map<String, Object> empParams = new HashMap<String, Object>();
			empParams.put("CompanyName", "tutum");
			empParams.put("CollectionBeanParam", new JRBeanCollectionDataSource(studentsGradeList.getCalificaciones()));

			InputStream input = ReportController.class.getResourceAsStream("/resources/" + "students-grades-details.jrxml");
			if (input == null) {
				// this is how we load file within editor (eg eclipse)
				log.info("bucando alternativa de resource...");
				input = ReportController.class.getClassLoader().getResourceAsStream("students-grades-details.jrxml");
				log.info(input == null ? "....no encontrada" : "encontrado!!!");
			}

			JasperPrint empReport = JasperFillManager.fillReport(JasperCompileManager.compileReport(input),
					empParams // dynamic parameters
					, new JREmptyDataSource());

			HttpHeaders headers = new HttpHeaders();
			// set the PDF format
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "students-grades-details.pdf");
			// create the report in PDF format
			return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

		} catch (Exception e) {
			log.error("!!Error report {}", e);
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
