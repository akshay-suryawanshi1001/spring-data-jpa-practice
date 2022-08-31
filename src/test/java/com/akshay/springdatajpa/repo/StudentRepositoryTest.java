package com.akshay.springdatajpa.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.akshay.springdatajpa.entity.Guardian;
import com.akshay.springdatajpa.entity.Student;

@SpringBootTest
//@DataJpaTest           //this anno can be used to mock the data saving in db, but here , instead of implementing rest api calls to save data in db 
							//we will just save actual data in the db using test classes only, so this anno is not needed here,
class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository studRepo;
	
	@Test
	@Disabled
	void saveStudent() {   
		Student student = Student.builder().firstName("aman").lastName("gupta")
				.emailId("amang@gmail.com")
				//.guardianName("nikhil").guradianEmail("nikhilg@gmail.com").guardianMobile("9876543234")
				.build();
		
		studRepo.save(student);
	}
	
	@Test
	void saveStudentWithEmbeddedGuardian() {
		Guardian guardian = Guardian.builder().name("deven").email("devenm@gmail.com").mobile("9835647257").build();
		
		Student student = Student.builder().firstName("ramesh").lastName("sharma")
				.emailId("rameshs@gmail.com")
				.guardian(guardian)
				.build();
		
		studRepo.save(student);
	}
	
	@Test
	void printAllStudents() {
		List<Student> students = studRepo.findAll();
		//students.stream().forEach(Student::toString);
		System.out.println(students);
	}
	
	@Test
	void findByFirstName() {
		List<Student> students = studRepo.findByFirstName("ramesh");
		System.out.println(students);
	}
	
	@Test
	void findByFirstNameContainingGivenString() {
		List<Student> students = studRepo.findByFirstNameContaining("man");
		System.out.println(students);
	}
	
	@Test
	void findByGuardianName() {
		List<Student> students = studRepo.findByGuardianName("rajesh");
		System.out.println(students);
	}
	
	@Test
	void findStudentByEmailJpqlQuery() {
		Student student = studRepo.getStudentByEmail("rameshs@gmail.com");
		System.out.println(student);
	}
	
	@Test
	void findStudentFirstNameByEmailJpqlQuery() {
		String student = studRepo.getStudentFirstNameByEmail("rameshs@gmail.com");
		System.out.println(student);
	}
	
	@Test
	void findStudentByEmailByNativeQuery() {
		Student student = studRepo.getStudentByEmailUsingNativeQuery("rameshs@gmail.com");
		System.out.println(student);
	}
	
	@Test
	void findStudentByEmailByNativeNamedParamQuery() {
		Student student = studRepo.getStudentByEmailUsingNamedParam("rameshs@gmail.com");
		System.out.println(student);
	}
	
	@Test
	void updateStudentFirstnameByEmailByNativeQuery() {
		int id = studRepo.updateStudentFirstnameByEmail("rameshan", "rameshs@gmail.com");
		System.out.println("update id:" + id + " in the db in student table...");
	}
	
}
