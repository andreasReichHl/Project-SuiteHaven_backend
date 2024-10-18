package org.example.suiteHaven.repositories;

import org.example.suiteHaven.entities.flat.HolidayFlat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayFlatRepository extends JpaRepository<HolidayFlat, Long> {
}
