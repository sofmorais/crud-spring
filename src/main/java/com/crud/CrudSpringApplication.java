package com.crud;

import com.crud.model.Vote;
import com.crud.model.VoteType;
import com.crud.repository.VoteRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.crud.model.Course;
import com.crud.repository.CourseRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
    CommandLineRunner initDatabase(CourseRepository courseRepository, VoteRepository voteRepository) {
        return args -> {
			 courseRepository.deleteAll();
			 Course front = new Course();
			front.setName(("Angular com Spring"));
			front.setCategory("Front-end");
			front.setDescription("Este curso aborda o desenvolvimento de aplicações web utilizando Angular em conjunto com o framework Spring.");
			courseRepository.save(front);

			// Inicializar votos para o curso
			Vote likeVote = new Vote();
			likeVote.setCourse(front);
			likeVote.setType(VoteType.LIKE);
			likeVote.setCreationDate(LocalDateTime.now());
			likeVote.setMachine("127.0.0.1"); // IP padrão ou de sua escolha

			voteRepository.save(likeVote);
		};
    }
}
