package org.example.superbnb.repositories;

import org.example.superbnb.entities.flat.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
