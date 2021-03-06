package com.crhms.seabow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi(){
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("x-auth-token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        tokenPar.defaultValue("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTcxOTcwNDMsImlhdCI6MTU1NzE1Mzg0MywidXNlcm5hbWUiOiJhZG1pbiJ9.vpmfi3We7pgovhLpFscJo4ycrDUMvC4dWJ5BXA1zNfY");
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.crhms.seabow"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Sping Boot Swagger Api Document1111 ")
                .description("for more information, please refer my blog:http://www.baidu.com")
                .termsOfServiceUrl("http://localhost:8081")
                .contact("admin")
                .version("1.0")
                .build();
    }
}