package com.example.library.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="library_card_number", 
    discriminatorType=DiscriminatorType.STRING)
@Table(name="account")
public abstract class Account {
    @Id
    private Long accountId;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private String email;
    private String phone;
}
