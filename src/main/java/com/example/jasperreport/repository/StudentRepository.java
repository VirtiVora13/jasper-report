package com.example.jasperreport.repository;

import com.example.jasperreport.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {


}