package com.crud.model;

import com.crud.model.enums.VoteType;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "vote")
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "host")
    private String host;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VoteType type;

    @Column(name = "insert_date")
    private Date insertDate;

    @Column(name = "update_date")
    private Date updateDate;

}

