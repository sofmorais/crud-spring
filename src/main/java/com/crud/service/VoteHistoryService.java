package com.crud.service;

import com.crud.model.Course;
import com.crud.model.VoteHistory;
import com.crud.model.dto.CreateVotacaoRequest;
import com.crud.repository.VoteHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoteHistoryService {

    private final VoteHistoryRepository historyRepository;

    public VoteHistory create(CreateVotacaoRequest request) {
        VoteHistory historico = VoteHistory.builder()
                .id(request.getId())
                .course(Course.builder().id(request.getIdCourse()).build())
                .host(request.getHost())
                .voteType(request.getType())
                .updateDate(new Date())
                .build();
        return historyRepository.save(historico);
    }

    public List<VoteHistory> findAll() {
        return this.historyRepository.findAll();
    }

    public void delete(UUID id){
        this.historyRepository.deleteById(id);
    }
}
