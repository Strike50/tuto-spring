package com.gfi.springit;

import com.gfi.springit.config.SpringitProperties;
import com.gfi.springit.domain.Comment;
import com.gfi.springit.domain.Link;
import com.gfi.springit.repository.CommentRepository;
import com.gfi.springit.repository.LinkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(SpringitProperties.class)
@EnableJpaAuditing
public class SpringitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringitApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository){
        return args -> {
            Link link = new Link("Getting started with SB 2","https://therealdanvega.com/spring-boot-2");
            linkRepository.save(link);

            Comment comment = new Comment("SB 2 is awesome !",link);
            commentRepository.save(comment);
            link.addComment(comment);

            System.out.println("link and a comment");
            System.out.println("=====================================================================");

        };
    }
}