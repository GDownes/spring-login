package com.codeaim.twitter.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.codeaim.twitter.login.model.User;
import com.codeaim.twitter.login.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories
public class Application implements CommandLineRunner
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception
    {
        userRepository.save(new User("gdownes", "hello", "password"));
        userRepository.findByUsername("gdownes");
    }
}
