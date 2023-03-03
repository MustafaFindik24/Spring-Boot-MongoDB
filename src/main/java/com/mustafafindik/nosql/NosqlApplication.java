package com.mustafafindik.nosql;

import com.mustafafindik.nosql.model.Address;
import com.mustafafindik.nosql.model.Gender;
import com.mustafafindik.nosql.Repository.StudentRepository;
import com.mustafafindik.nosql.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class NosqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NosqlApplication.class, args);
	}

	@Bean
	CommandLineRunner runner (StudentRepository repository){
		return args -> {
			Address address = new Address(
					"Turkey",
					"Istanbul"
			);
			Student student = new Student(
					"Melih",
					"Fındık",
					"melihfindik@gmail.com",
					Gender.MALE,
					address,
					BigDecimal.TEN,
					LocalDateTime.now(),
					List.of("Math", "English","Turkish","Science")
			);
			repository.insert(student);
		};
	}
}
