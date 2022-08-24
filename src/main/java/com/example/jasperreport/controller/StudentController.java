package com.example.jasperreport.controller;

import com.example.jasperreport.model.Student;
import com.example.jasperreport.repository.StudentRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.PathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student)
    {
        studentRepository.save(student);
        return student;
    }

    String temp = "vedant.pdf";
    String path = "/Users/virti.vora/Desktop/jasper-report/src/main/resources/Pdfs/";
    @RequestMapping(value = "/getpdf",produces = MediaType.APPLICATION_PDF_VALUE,method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getPdf() throws JRException, IOException, InterruptedException {
        List<Student> student = studentRepository.findAll();

        String filePath = "/Users/virti.vora/Desktop/jasper-report/src/main/resources/Data.jrxml";
        Map<String,Object> parameters = new HashMap<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        parameters.put("studentName", "Updated On :"+dtf.format(now));

        JRBeanCollectionDataSource dataSource= new JRBeanCollectionDataSource(student);
        JasperReport jasperReport= JasperCompileManager.compileReport(filePath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint,path.concat(temp));

        PathResource pdf = new PathResource(path.concat(temp));
        return ResponseEntity.ok().body(new InputStreamResource(pdf.getInputStream()));
    }

}