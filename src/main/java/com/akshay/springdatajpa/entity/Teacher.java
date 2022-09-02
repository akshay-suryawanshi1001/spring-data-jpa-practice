package com.akshay.springdatajpa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Teacher {
	@Id
	@SequenceGenerator(name = "teacher_seq", sequenceName = "teacher_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
	private Long teacherId;
	private String firstName;
	private String lastName;
	
	@OneToMany(cascade = CascadeType.ALL)   //this join column is diff from onetoonce join column
	@JoinColumn(name = "teacher_id",   // here single teacher can teach many sunject, so for multiple courses in course table can have same teacher_id column value in course table
	referencedColumnName = "teacherId") //the above create column will refer value of techer from teacherId member of this class
	private List<Course> courses;      //so basically this will create one more column in course table as teacher_id

}
