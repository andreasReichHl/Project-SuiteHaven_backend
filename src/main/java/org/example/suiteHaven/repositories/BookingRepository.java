package org.example.suiteHaven.repositories;

import org.example.suiteHaven.entities.booking.Booking;
import org.example.suiteHaven.entities.flat.HolidayFlat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByHolidayFlatId(Long flatId);
}
