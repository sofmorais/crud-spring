package com.crud.controller;

import com.crud.model.InformacaoApi;
import com.crud.model.dto.ActuatorInfoDto.BuildInfoDto;
import com.crud.model.enums.TipoAmbiente;
import com.crud.service.InformacaoApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/actuator")
public class InformacaoApiController {

    private final InformacaoApiService informacaoApiService;

    public InformacaoApiController(InformacaoApiService actuatorService) {
        this.informacaoApiService = actuatorService;
    }

    @GetMapping("/portal")
    public BuildInfoDto getPortalInfo(@RequestParam TipoAmbiente ambiente) {
        return informacaoApiService.getPortalInfo(ambiente);
    }

    @GetMapping("/autorizacao")
    public BuildInfoDto getAutorizacaoInfo(@RequestParam TipoAmbiente ambiente) {
        return informacaoApiService.getAutorizacaoInfo(ambiente);
    }


    @PostMapping("/cadastro")
    public ResponseEntity<InformacaoApi> criarApiUrl(@RequestBody InformacaoApi informacaoApi) {
        InformacaoApi createdApiUrl = this.informacaoApiService.createApiUrl(informacaoApi);
        return ResponseEntity.ok(createdApiUrl);
    }
}
