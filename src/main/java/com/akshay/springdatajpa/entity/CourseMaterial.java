package com.akshay.springdatajpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

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
@ToString(exclude = "course")  //here we say that when course member is lazy initialized and we get only coursematerial from db then exclude toString for course otherwise getting the data will fail
public class CourseMaterial {
	
	@Id
	@SequenceGenerator(name = "course_material_seq", sequenceName = "course_material_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_material_seq")
	private Long courseMaterialId;
	private String url;
	
	//now course_material cannot exist without course table so here we need to add courseId as the foreign key from course table
	@OneToOne(cascade = CascadeType.ALL, //this eventually mean save the course first and then save coursematerial
			fetch = FetchType.EAGER,
			optional = false)          //now due to this line for every courseMaterila course is mandatory  
	@JoinColumn(
			name = "course_id" ,    /* foreign key column name in this table*/
			referencedColumnName = "courseId"  /*coulmn name from the course table which we want to refer here*/
	)
	private Course course;
}
