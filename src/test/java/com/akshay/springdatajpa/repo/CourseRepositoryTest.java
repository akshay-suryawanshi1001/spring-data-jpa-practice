package com.akshay.springdatajpa.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.akshay.springdatajpa.entity.Course;

@SpringBootTest
class CourseRepositoryTest {

	@Autowired
	private CourseRepository repo;
	
	@Test
	void printAllCourses() {
		List<Course> courses = repo.findAll();
		System.out.println("courses are :" + courses);
	}

}
