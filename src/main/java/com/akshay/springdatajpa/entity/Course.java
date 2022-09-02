package com.akshay.springdatajpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


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
	@ToString.Exclude             //otherwise throws failed to lazily initialize a collection of role
	private CourseMaterial courseMaterial;
	
	//for every oneToMany rel we can alternatively create manyToOne relationship, but should implement either one
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)   
	@JoinColumn(name = "teacher_id2",      /*this will be the foreign key in this table */
	referencedColumnName = "teacherId")    //mention here ,from which of Teacher table teacher we are putting value in teacher_id2 column
	@ToString.Exclude
	private Teacher teacher;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "course_student_mapping",   /* name of the new mapping table to be created*/
	joinColumns = @JoinColumn(name = "course_id",    /*name of the column containing id of course table*/
			referencedColumnName = "courseId"),     //name of the member from Course class to put in course_id
	inverseJoinColumns = @JoinColumn(name = "student_id",  /*name of the column containing id of student table*/
			referencedColumnName = "studentId")
	)              
	private List<Student> students;
	
	public void addStudent(Student student) {   //for convenience of adding a student
		if(student != null) students = new ArrayList<Student>();
		students.add(student);
			
	}
	
}
