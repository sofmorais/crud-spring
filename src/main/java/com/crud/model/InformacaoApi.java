package com.crud.model;

import com.crud.model.enums.TipoAmbiente;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InformacaoApi {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "url")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "ambiente")
    private TipoAmbiente ambiente;
}
