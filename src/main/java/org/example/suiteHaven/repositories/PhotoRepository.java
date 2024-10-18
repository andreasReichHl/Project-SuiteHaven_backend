package org.example.suiteHaven.repositories;

import org.example.suiteHaven.entities.flat.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
