package com.akshay.springdatajpa.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.CascadeType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.akshay.springdatajpa.entity.Course;
import com.akshay.springdatajpa.entity.CourseMaterial;

@SpringBootTest
class CourseMaterialRepositoryTest {
	
	@Autowired
	private CourseMaterialRepository repo;

	@Test
	void saveCourseMaterial() {          //in order to save coursematerial we must have a course also
		Course course = Course.builder()
				.title("DSA")
				.credit(6)
				.build();
		CourseMaterial cm = CourseMaterial.builder()
				.url("www.udemy.com")
				.course(course)
				.build();
		
		repo.save(cm); //here we should save course in db first then save coursemateial, cascade = CascadeType.ALL on courseid in coursematerial does that
		
	}
	
	@Test
	public void printAllCourseMaterial() {
		List<CourseMaterial> cm = repo.findAll(); 
		System.out.println("courseMaterials : " + cm);
	}
}
