package org.example.superbnb.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Management {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
