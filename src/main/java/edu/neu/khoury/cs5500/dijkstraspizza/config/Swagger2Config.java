package edu.neu.khoury.cs5500.dijkstraspizza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo())
        .tags(new Tag("ingredient", "Provides CRUD operations for Ingredient objects"),
            new Tag("menu", "Provides CRUD operations for menu objects"),
            new Tag("order", "Provides CRU operations for order objects (Delete not supported)"),
            new Tag("pizza", "Provides CRUD operations for pizza objects"),
            new Tag("pizza-store", "Provides CRUD operations for pizza store objects"),
            new Tag("price-calculator", "Provides pricing details for pizzas and specials"),
            new Tag("pizza-size", "Provides CRUD operations for pizza sizes")
        );
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Dijkstra's Pizza API")
        .description("A backend API for Dijkstra's Pizza Company")
        .version("0.1.0")
        .build();
  }
}
