package com.akshay.springdatajpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akshay.springdatajpa.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
