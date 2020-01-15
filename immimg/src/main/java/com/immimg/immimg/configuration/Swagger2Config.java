package com.immimg.immimg.configuration;

import lombok.Data;
import net.bytebuddy.description.field.FieldDescription;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @version v1.0
 * @ProjectName: springboot0110
 * @ClassName: Swagger2Config
 * @Description: TODO(一句话描述该类的功能)
 * @Author: Nlqiong
 * @Date: 2020/1/12 19:48
 */
@Configuration
@EnableSwagger2
@Data
@ConfigurationProperties(prefix = "swagger2")
public class Swagger2Config{

    private String basePackage;
    private String title;
    private String description;
    private String version;
    /*private String url;
    private String author;
    private String email;*/

    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
            return new ApiInfoBuilder()
                    .title(title)
                    .description(description)
                    .version(version)
                    /*.contact(new Contact(author,url,email))*/
                    .build();
    }
}
