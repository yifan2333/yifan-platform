package com.yifan.swagger.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

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

/**
 * @author fredlau
 * @version 1.0.0
 * @date 17/12/12
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class BaseSwaggerConfig {

    @Bean
    @ConditionalOnMissingBean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yifan"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(getGlobalParams());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口测试")
                .description("restful apis")
                .version("1.0")
                .build();
    }

    private List<Parameter> getGlobalParams(){
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization")
                .description("Bearer Token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("Bearer ")
                .required(false)
                .build();
        pars.add(tokenPar.build());
        return pars;
    }
}
