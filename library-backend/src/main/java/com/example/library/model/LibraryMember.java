package com.example.library.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("not null")
public class LibraryMember extends Account {
}
