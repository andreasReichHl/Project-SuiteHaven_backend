package org.example.suiteHaven.services;

import org.example.suiteHaven.dtos.BookingRequestDto;
import org.example.suiteHaven.dtos.BookingResponseDto;
import org.example.suiteHaven.entities.booking.Booking;
import org.example.suiteHaven.entities.flat.HolidayFlat;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.exception.BookingConflictException;
import org.example.suiteHaven.repositories.BookingRepository;
import org.example.suiteHaven.repositories.HolidayFlatRepository;
import org.example.suiteHaven.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final HolidayFlatRepository holidayFlatRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, HolidayFlatRepository holidayFlatRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.holidayFlatRepository = holidayFlatRepository;
        this.userRepository = userRepository;
    }

    // new booking

    public BookingResponseDto createNewBooking(BookingRequestDto dto, Authentication authentication) {
        try {
            if(dto.checkOut().isBefore(dto.checkIn())) throw new IllegalStateException("Check-out date must be after check-in date.");

            HolidayFlat holidayFlat = holidayFlatRepository.findById(dto.flatId()).orElseThrow(NoSuchElementException::new);
            User user = userRepository.findByEmail(authentication.getName()).orElseThrow(NoSuchElementException::new);

            if (holidayFlat.getUser().equals(user))
                throw new IllegalStateException("Owner cannot book his own holiday flat");

            if (hasOverlappingBookings(holidayFlat, dto.checkIn(), dto.checkOut())) {
                throw new BookingConflictException();
            }

            Booking booking = newBooking(dto, holidayFlat);
            booking.setUser(user);
            bookingRepository.save(booking);
            return new BookingResponseDto(holidayFlat.getTitle(), booking.getCheckIn(), booking.getCheckOut(), booking.getNight(), booking.getTotalPrice());
        } catch (Exception e) {
            System.err.println("Error creating booking: " + e.getMessage());
            throw e;
        }
    }


    private Booking newBooking(BookingRequestDto dto, HolidayFlat holidayFlat) {
        Booking booking = new Booking();
        booking.setCheckIn(dto.checkIn());
        booking.setCheckOut(dto.checkOut());
        booking.setPerson(dto.person());
        booking.setPricePerNight(holidayFlat.getPrice());
        booking.setFinalCleaningCost(holidayFlat.getFinalCleaningPrice());
        booking.setHolidayFlat(holidayFlat);
        return booking;
    }

    public boolean isOverlapping(Booking booking, LocalDate checkIn, LocalDate checkOut) {
        return !(booking.getCheckIn().isAfter(checkOut) || booking.getCheckOut().isBefore(checkIn));
    }

    public boolean hasOverlappingBookings(HolidayFlat holidayFlat, LocalDate checkIn, LocalDate checkOut) {
        List<Booking> bookings = bookingRepository.findAllByHolidayFlatId(holidayFlat.getId());
        return bookings.stream()
                .anyMatch(booking -> isOverlapping(booking, checkIn, checkOut));
    }


    // update booking

    // delete booking

    // get booking
}
