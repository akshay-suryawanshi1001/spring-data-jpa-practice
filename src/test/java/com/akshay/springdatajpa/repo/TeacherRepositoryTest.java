package com.akshay.springdatajpa.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.akshay.springdatajpa.entity.Course;
import com.akshay.springdatajpa.entity.Teacher;

@SpringBootTest
class TeacherRepositoryTest {
	
	@Autowired
	private TeacherRepository repository;
	
	@Test
	void saveTeacherWith2Courses() {
		Course course1 = Course.builder()
				.title("MA")
				.credit(5)
				.build();
		Course course2 = Course.builder()
				.title("CN")
				.credit(6)
				.build();
		List<Course> courses =  new ArrayList<Course>();
		courses.add(course1);
		courses.add(course2);
		
		Teacher teacher = Teacher.builder()
				.firstName("tuka")
				.lastName("deore")
				//.courses(courses)
				.build();
		
		repository.save(teacher);
	}

}
