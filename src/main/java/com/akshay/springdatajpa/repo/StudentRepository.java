package com.akshay.springdatajpa.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akshay.springdatajpa.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	public List<Student> findByFirstName(String firstName);
	
	public List<Student> findByFirstNameContaining(String firstName);
	
	List<Student> findByLastNameNotNull();
	
	List<Student> findByGuardianName(String guardianname);  // this is for embeded table Guardian and field name
	
	Student findByFirstNameAndLastName(String firstname, String lastname);
	
	@Query("select s from Student s where s.emailId = ?1 ")    //JPQL query
	Student getStudentByEmail(String email);
	
	@Query("select s.firstName from Student s where s.emailId = ?1 ")    //JPQL query
	String getStudentFirstNameByEmail(String email);
	
	@Query(value = "select * from Student where email_id = ?1 " , nativeQuery = true)    //Native query
	Student getStudentByEmailUsingNativeQuery(String email);
	
	@Query(value = "select * from Student where email_id = :emailId " , nativeQuery = true)    //Native named param
	Student getStudentByEmailUsingNamedParam(@Param("emailId") String email);
	
	@Modifying
	@Transactional
	@Query(value = "update student set first_name = ?1  where email_id = ?2 ", nativeQuery = true) //update using native query
	int updateStudentFirstnameByEmail(String firstname, String email);
};
