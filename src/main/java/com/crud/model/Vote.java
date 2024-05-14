package com.crud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_idVote")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoteType type;

    @Column(nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(nullable = false)
    private String machine;

//    public void setCurso(Course course) {
//    }
//
//    public void setTipo(VoteType voteType) {
//
//    }
//
//    public void setDataCriacao(LocalDateTime now) {
//    }
//
//    public void setMaquina(String maquina) {
//    }
}

