package com.mustafafindik.nosql;

import com.mustafafindik.nosql.model.Address;
import com.mustafafindik.nosql.model.Gender;
import com.mustafafindik.nosql.Repository.StudentRepository;
import com.mustafafindik.nosql.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class NosqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NosqlApplication.class, args);
	}

	@Bean
	CommandLineRunner runner (StudentRepository repository,
							  MongoTemplate mongoTemplate){
		return args -> {
			Address address = new Address(
					"Turkey",
					"Istanbul"
			);
			String email = "Fatmafindik@gmail.com";

			Student student = new Student(
					"Fatma",
					"Fındık",
					email,
					Gender.MALE,
					address,
					BigDecimal.TEN,
					LocalDateTime.now(),
					List.of("Math", "English","Turkish")
			);

			Query query = new Query();
			query.addCriteria(Criteria.where("email").is(email));

			List<Student> students= mongoTemplate.find(query, Student.class);

			if (students.size()>1){
				throw new IllegalStateException("Bu email ile birden fazla öğrenci bulundu : " + email);
			}
			if (students.isEmpty()){
				System.out.println("Öğrenci ekleniyor : " + student);
				repository.insert(student);
			} else {
				System.out.println(student + " öğrencisi kayıtlı.");
			}
		};
	}
}
