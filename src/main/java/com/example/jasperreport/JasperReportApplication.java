package com.example.jasperreport;

import com.example.jasperreport.model.Student;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class JasperReportApplication {

	public static void main(String[] args) throws JRException {
		SpringApplication.run(JasperReportApplication.class, args);


	}

}
