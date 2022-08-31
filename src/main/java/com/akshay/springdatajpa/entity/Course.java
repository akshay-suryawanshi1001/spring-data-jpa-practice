package com.akshay.springdatajpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
	
	@Id
	@SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
	@Column(name = "courseId")                   //even after this the column created in db is course_id bcz of ImprovedNamingStrategy of hibernate where it add "_" for camelCases
	private Long courseId;
	private String title;
	private Integer credit;
	//until this point there is unidirectional mapping between course and course material, 
	//from below lines we are making it bidirectional mapping
	@OneToOne(mappedBy = "course") //membername from the @joincloumn class
	private CourseMaterial courseMaterial;
}
