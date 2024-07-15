package com.crud.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ActuatorInfoDto {
    @JsonProperty("build")
    private BuildInfoDto build;

    @Setter
    @Getter
    public static class BuildInfoDto {
        private String name;
        private String time;
        private String version;
    }
}



//package com.crud.model.dto;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.Date;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import com.crud.model.dto.ActuatorInfoDto.BuildInfoDto;
//
//
//@Data
//@Getter
//@Setter
//public class ActuatorInfoDto {
//    @JsonProperty("build")
//    private BuildInfoDto buildInfo;
//
//    private String name;
//    private String version;
//    private Date time;
//
//    public BuildInfoDto getBuildInfo() {
//        return buildInfo;
//    }
//
//    public static class BuildInfoDto {
//        private String name;
//        private Date time;
//        private String version;
//
//    }
//}