package org.example.suiteHaven.repositories;

import org.example.suiteHaven.entities.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
