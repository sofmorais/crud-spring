package com.crud.service;

import com.crud.model.Course;
import com.crud.model.Vote;
import com.crud.model.dto.CreateVotacaoRequest;
import com.crud.model.enums.VoteType;
import com.crud.repository.VoteRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final VoteHistoryService historyService;

    public void create(CreateVotacaoRequest request, String host) {

        Vote vote = this.voteRepository.findByCourse_IdAndHost(request.getId(), request.getHost());
        String ipAddress = getClientIp(host);

        if(vote == null) {
            this.voteRepository.save(Vote.builder()
                .id(request.getId())
                .course(Course.builder().id(request.getIdCourse()).build())
                .host(ipAddress)
                .type(request.getType())
                .updateDate(new Date())
                .insertDate(new Date())
                .build());

        this.historyService.create(request);
        }

    }

    public void update(final CreateVotacaoRequest request, HttpServletRequest host) {
        Vote vote = this.findByIdCourseAndHost(request.getIdCourse(), request.getHost());

        if (vote != null) {
            vote.setType(request.getType());
            vote.setUpdateDate(new Date());

            this.voteRepository.save(vote);
            this.historyService.create(request);
        }
    }

    public void delete(UUID id) {
        this.voteRepository.deleteById(id);
    }

    public Optional<Vote> findById(UUID id) {
        return this.voteRepository.findById(id);
    }

    public Vote findByIdCourseAndHost(UUID courseId, String host) {
        return this.voteRepository.findByCourse_IdAndHost(courseId, host);
    }

    public List<Vote> findAll() {
        return this.voteRepository.findAll();
    }

    public Vote findByType(VoteType type){
        return this.voteRepository.findByType(type);
    }

    private String getClientIp(String host) {
        String remoteAddr = "";

        if (host != null && !host.isEmpty()) {
            remoteAddr = host;
        }
        return remoteAddr;
    }

}
