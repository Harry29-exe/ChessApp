package com.kw.DocumentRepository.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class PersistenceConfiguration {

//    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("doc_repo");
    }

    @Bean
    public TestBean getTestBean() {
        return new TestBean();
    }
}
