package com.akshay.springdatajpa.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student", uniqueConstraints = @UniqueConstraint(name="emailid_unique", columnNames = "email_id") )
public class Student {
	
	@Id
	@SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)    //create customer sequence
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq" /*this is name from sequenceGenerator anno*/)
	private Long studentId;
	private String firstName;
	private String lastName;
	
	@Column(name = "email_id", nullable = false)
	private String emailId;
	
	/*
	private String guardianName;
	private String guradianEmail;
	private String guardianMobile; */  //instead of doing like this create a guardian class with these fields and then embed it here
	
	@Embedded
	private Guardian guardian;
}
