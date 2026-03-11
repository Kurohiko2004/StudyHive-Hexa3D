//package com.sep.commonModule.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class OpenApiConfig {
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("Smart Education Platform (SEP) API")
//                        .version("1.0.0")
//                        .description("Tài liệu API cho hệ thống quản lý giáo dục thông minh SEP. " +
//                                "Hệ thống hỗ trợ quản lý lớp học, học liệu và chấm điểm bằng AI.")
//                        .license(new Info().getLicense()));
//    }
//}