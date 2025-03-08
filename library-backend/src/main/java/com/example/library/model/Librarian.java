package com.example.library.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("null")
public class Librarian extends Account {
}
