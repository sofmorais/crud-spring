package com.crud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.crud.model.Course;
import com.crud.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
    CommandLineRunner initDatabase(CourseRepository courseRepository) {
        return args -> {
			 courseRepository.deleteAll();

			 Course c = new Course();
			 c.setName(("Angular com Spring"));
			 c.setCategory("Front-end");
			 c.setDescription("Este curso aborda o desenvolvimento de aplicações web utilizando Angular em conjunto com o framework Spring.");
			courseRepository.save(c);
		};
    }
}
