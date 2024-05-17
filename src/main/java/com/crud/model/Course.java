package com.crud.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Id;


import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course implements  Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "category", nullable = false)
    @Pattern(regexp = "Back-end|Front-end")
    private String category;

    @Builder.Default
    @Column(name = "status", nullable = false)
    @Pattern(regexp = "Active|Inactive")
    private String status = "Active";

    @Column(name = "description", nullable = false)
    private String description;

}