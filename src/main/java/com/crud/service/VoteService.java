package com.crud.service;

import com.crud.model.Course;
import com.crud.model.Vote;
import com.crud.model.VoteType;
import com.crud.repository.CourseRepository;
import com.crud.repository.VoteRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.checkerframework.checker.index.qual.Positive;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;


public class VoteService {
    private final VoteRepository voteRepository;
    private final CourseRepository courseRepository;
    private final HttpServletRequest request;

    @Autowired
    public VoteService(VoteRepository voteRepository, CourseRepository courseRepository, HttpServletRequest request) {
        this.voteRepository = voteRepository;
        this.courseRepository = courseRepository;
        this.request = request;
    }

    public List<Vote> listVotes() {
        return voteRepository.findAll();
    }

    public Optional<Vote> findVoteById(Long id) {
        return voteRepository.findById(id);
    }

    public Optional<Vote> updateVote(Long id, @Valid Vote vote) {
        return voteRepository.findById(id)
                .map(existingVote -> {
                    existingVote.setType(vote.getType());
                    existingVote.setCreationDate(LocalDateTime.now());
                    return voteRepository.save(existingVote);
                });
    }

    public boolean deleteVote(Long id) {
        return voteRepository.findById(id)
                .map(existingVote -> {
                    voteRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

    public void createVote(@NotNull Long courseId, VoteType voteType,  String ipAddress) {
        Course curso = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        if (curso.getVotes().stream().anyMatch(vote -> vote.getMachine().equals(ipAddress))) {
            throw new RuntimeException("Este IP já votou neste curso");
        }

        if (voteType == VoteType.LIKE) {
            curso.setLikes(curso.getLikes() + 1);
        } else {
            curso.setDislikes(curso.getDislikes() + 1);
        }

        Vote vote = new Vote();
        vote.setCourse(curso);
        vote.setType(voteType);
        vote.setCreationDate(LocalDateTime.now());
        vote.setMachine(ipAddress);

        curso.getVotes().add(vote);

        voteRepository.save(vote);
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
