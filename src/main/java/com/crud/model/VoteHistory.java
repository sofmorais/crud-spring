package com.crud.model;

import com.crud.model.enums.VoteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @Column(name = "host")
    private String host;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VoteType voteType;

    @Column(name = "update_date")
    private Date updateDate;
}
