package com.crud.service;

import com.crud.model.InformacaoApi;
import com.crud.model.dto.ActuatorInfoDto;
import com.crud.model.dto.ActuatorInfoDto.BuildInfoDto;
import com.crud.model.enums.TipoAmbiente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.crud.repository.InformacaoApiRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class InformacaoApiService {

    private final InformacaoApiRepository apiRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public ActuatorInfoDto getActuatorInfo(String url) {
        return restTemplate.getForObject(url, ActuatorInfoDto.class);
    }

    public BuildInfoDto getPortalInfo(TipoAmbiente ambiente) {
        String url = findUrlByAmbienteAndPath(ambiente, "/portal/actuator/info");
        ActuatorInfoDto actuatorInfo = getActuatorInfo(url);
        return actuatorInfo.getBuild();
    }

    public BuildInfoDto getAutorizacaoInfo(TipoAmbiente ambiente) {
        String url = findUrlByAmbienteAndPath(ambiente, "/autorizacao/actuator/info");
        ActuatorInfoDto actuatorInfo = getActuatorInfo(url);
        return actuatorInfo.getBuild();
    }

    private String findUrlByAmbienteAndPath(TipoAmbiente ambiente, String path) {
        return apiRepository.findByAmbiente(ambiente)
                .stream()
                .map(InformacaoApi::getUrl)
                .filter(url -> url.contains(path))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Erro: " + path + ambiente));
    }

    public InformacaoApi createApiUrl(InformacaoApi informacaoApi) {
        return apiRepository.save(informacaoApi);
    }

//    public BuildInfoDto getPortalInfo(TipoAmbiente ambiente) {
//        String url = this.apiRepository.findByAmbiente(ambiente)
//                .stream()
//                .map(InformacaoApi::getUrl)
//                .filter(endpointUrl -> endpointUrl.contains("/portal/actuator/info"))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("URL not found for the given environment: " + ambiente));
//
//        ActuatorInfoDto actuatorInfo = getActuatorInfo(url);
//        return actuatorInfo != null ? actuatorInfo.getBuild() : null;
//
//    }
//
//    public BuildInfoDto getAutorizacaoInfo(TipoAmbiente ambiente) {
//        String url = this.apiRepository.findByAmbiente(ambiente)
//                .stream()
//                .map(InformacaoApi::getUrl)
//                .filter(endpointUrl -> endpointUrl.contains("/autorizacao/actuator/info"))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("URL not found for the given environment: " + ambiente));
//
//        ActuatorInfoDto actuatorInfo = getActuatorInfo(url);
//        return actuatorInfo != null ? actuatorInfo.getBuild() : null;
//
//    }




//    public ActuatorInfoDto getActuatorInfo(String url) {
//        ActuatorInfoDto response = restTemplate.getForObject(url, ActuatorInfoDto.class);
//        return response;
//    }
//
//    public ActuatorInfoDto getPortalInfo(TipoAmbiente ambiente) {
//        String url = this.apiRepository.findByAmbiente(ambiente)
//                .stream()
//                .filter(endpoint -> endpoint.getUrl().contains("/portal/actuator/info"))
//                .map(InformacaoApi::getUrl)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("URL not found for the given environment"));
//        return getActuatorInfo(url);
//    }
//
//    public ActuatorInfoDto getAutorizacaoInfo(TipoAmbiente ambiente) {
//        String url = this.apiRepository.findByAmbiente(ambiente)
//                .stream()
//                .filter(endpoint -> endpoint.getUrl().contains("/autorizacao/actuator/info"))
//                .map(InformacaoApi::getUrl)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("URL not found for the given environment"));
//        return getActuatorInfo(url);
//    }
//
//    public List<InformacaoApi> findByAmbiente (TipoAmbiente ambiente) {
//        return this.apiRepository.findByAmbiente(ambiente);
//    }
}