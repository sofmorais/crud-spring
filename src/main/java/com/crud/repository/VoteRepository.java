package com.crud.repository;

import com.crud.model.Vote;
import com.crud.model.enums.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {

    Vote findByCourse_IdAndHost(UUID courseId, String host);

    Vote findByType(VoteType type);
}