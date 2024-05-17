package com.crud.service;

import com.crud.model.Course;
import com.crud.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public void findById(UUID id) {
        courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
    }

    public Course create(final Course course) {
        Course curso = this.courseRepository.findById(course.getId()).orElse(null);

        if (curso == null) {
            this.courseRepository.save(Course.builder()
                    .id(course.getId())
                    .category(course.getCategory())
                    .description(course.getDescription())
                    .name(course.getName())
                    .status(course.getStatus())
                    .build());
        }
        return courseRepository.save(course);
    }

    public Optional<Course> update(UUID id, Course course) {
        Optional<Course> curso = this.courseRepository.findById(id);

        if (curso.isPresent()) {
        this.courseRepository.save(Course.builder()
            .category(course.getCategory())
            .description(course.getDescription())
            .name(course.getName())
            .status(course.getStatus())
            .build());
        }
        return curso;
        /*return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    recordFound.setDescription(course.getDescription());
                    return courseRepository.save(recordFound);
                });*/
    }

    public void delete(UUID id) {
        this.courseRepository.deleteById(id);
      /*  return courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    return true;
                })
                .orElse(false);*/
    }
}
