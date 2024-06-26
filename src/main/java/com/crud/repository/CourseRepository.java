package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.model.Course;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID>{
    
}
