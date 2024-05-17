package com.crud.model.dto;

import com.crud.model.enums.VoteType;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateVotacaoRequest {

    private UUID id;
    private UUID idCourse;
    private String host;
    private VoteType type;
}
