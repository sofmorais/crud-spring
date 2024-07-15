package com.crud.repository;

import com.crud.model.InformacaoApi;
import com.crud.model.enums.TipoAmbiente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InformacaoApiRepository extends CrudRepository<InformacaoApi, UUID> {
    List<InformacaoApi> findByAmbiente(TipoAmbiente ambiente);
}
