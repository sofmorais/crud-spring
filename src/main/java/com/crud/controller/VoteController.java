package com.crud.controller;

import com.crud.model.Course;
import com.crud.model.Vote;
import com.crud.model.VoteType;
import com.crud.repository.VoteRepository;
import com.crud.service.CourseService;
import com.crud.service.VoteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/votes")
public class VoteController {

    private final VoteService voteService;
    private final VoteRepository voteRepository

/*    @Autowired
    private VoteService voteService;*/

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    public @ResponseBody List<Vote> list() {
        return voteService.listVotes();
    }

    public List<Vote> getVotesForCourse(Long courseId) {
        return voteRepository.findByCourseId(courseId);
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


    @PostMapping("/votes/like/{courseId}")
    public ResponseEntity<Vote> createVoteLike(@RequestParam @PathVariable Long courseId, @RequestHeader("X-Forwarded-For") String ipAddress) {
        voteService.createVote(courseId, VoteType.LIKE, ipAddress);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/votes/dislike/{courseId}")
    public ResponseEntity<Vote> createVoteDislike(@RequestParam @PathVariable Long courseId, @RequestHeader("X-Forwarded-For") String ipAddress) {
        voteService.createVote(courseId, VoteType.DISLIKE, ipAddress);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Vote>> getVotesForCourse(@PathVariable Long courseId) {
        List<Vote> votes = voteService.getVotesForCourse(courseId);
        return ResponseEntity.ok().body(votes);
    }


}