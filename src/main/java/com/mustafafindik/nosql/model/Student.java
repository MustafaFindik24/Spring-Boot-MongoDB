package com.mustafafindik.nosql.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Document
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true) // benzersiz bir email kontrolü için belirtildi.
    private String email;
    private Gender gender;
    private Address address;
    private BigDecimal totalBooks;
    private LocalDateTime created;
    private List<String> favouriteSubjects;

    public Student(String firstName, String lastName, String email, Gender gender, Address address,
                   BigDecimal totalBooks, LocalDateTime created, List<String> favouriteSubjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.totalBooks = totalBooks;
        this.created = created;
        this.favouriteSubjects = favouriteSubjects;
    }
}
