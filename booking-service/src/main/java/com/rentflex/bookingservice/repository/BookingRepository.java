package com.rentflex.bookingservice.repository;

import com.rentflex.bookingservice.model.Booking;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM bookings WHERE userId = :userId", nativeQuery = true)
    Optional<Booking> getBookingsByUser(Long userId);
}
