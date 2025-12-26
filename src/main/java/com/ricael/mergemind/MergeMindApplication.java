package com.ricael.mergemind;

import com.ricael.mergemind.dto.request.UserRequest;
import com.ricael.mergemind.repository.ApplicationRepository;
import com.ricael.mergemind.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class MergeMindApplication {

    public static void main(String[] args) {
        SpringApplication.run(MergeMindApplication.class, args);

    }

    @Bean
    CommandLineRunner run(UserServices userServices) {
        return args -> {

            userServices.updateUser( 4L ,new UserRequest("Ricael", "menezes@gmail.com", "aaa", "Victor123", "USER", "ACTIVE"));

            System.out.println("sucesso!");
        };
    }
}
