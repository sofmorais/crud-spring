package com.crud.controller;

import com.crud.model.Vote;
import com.crud.model.dto.CreateVotacaoRequest;
import com.crud.model.enums.VoteType;
import com.crud.repository.VoteRepository;
import com.crud.service.CourseService;
import com.crud.service.VoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/votes")
public class VoteController {

    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Vote> create(@Validated @RequestBody CreateVotacaoRequest request, @RequestHeader("X-Forwarded-For") String host) {
        this.voteService.create(request, host);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vote> update(@Validated @RequestBody CreateVotacaoRequest request, HttpServletRequest host) {
        this.voteService.update(request, host);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findByType/{type}")
    public ResponseEntity<Vote> findByType(@PathVariable VoteType type) {
        this.voteService.findByType(type);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idCourse}/{host}")
    public ResponseEntity<Vote> findByCourseAndHostId(@PathVariable UUID idCourse, @PathVariable String host) {
        this.voteService.findByIdCourseAndHost(idCourse, host);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAllVotes")
    public ResponseEntity<List<Vote>> findAll() {
        this.voteService.findAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.voteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}