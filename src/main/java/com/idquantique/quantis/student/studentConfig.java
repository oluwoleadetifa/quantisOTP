package com.idquantique.quantis.student;

import com.idquantique.quantis.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

import static java.time.Month.JANUARY;

@Configuration
public class studentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mariam = new Student(
                    "Mariam",
                    "mariam.jude@gmail.com",
                    LocalDate.of(2000, JANUARY, 5),
                    "shidibande"

            );
            Student alex = new Student(
                    "alex",
                    "alex.segura@gmail.com",
                    LocalDate.of(2004, JANUARY, 5),
                    "sureboy"

            );

            repository.saveAll(Arrays.asList(mariam, alex));
        };
    }
}
