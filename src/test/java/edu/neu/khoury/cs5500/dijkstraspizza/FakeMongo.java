package edu.neu.khoury.cs5500.dijkstraspizza;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public abstract class FakeMongo extends AbstractMongoClientConfiguration {

  @Override
  protected String getDatabaseName() {
    return "mockDB";
  }

  @Bean
  public MongoClient mongo() {
    Fongo fongo = new Fongo("mockDB");
    return fongo.getMongo();
  }
}