package com.crud.repository;

import com.crud.model.VoteHistory;
import com.crud.model.dto.CreateVotacaoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoteHistoryRepository extends JpaRepository<VoteHistory, UUID> {
}
