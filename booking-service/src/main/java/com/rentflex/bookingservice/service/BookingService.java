package com.rentflex.bookingservice.service;

import com.rentflex.bookingservice.dto.BookingRequestDTO;
import com.rentflex.bookingservice.dto.BookingResponseDTO;
import com.rentflex.bookingservice.dto.CancelBookingRequestDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    BookingResponseDTO createBooking(BookingRequestDTO request);

    BookingResponseDTO getBookingById(Long bookingId);

    List<BookingResponseDTO> getBookingsByUser(Long userId);

    BookingResponseDTO cancelBooking(CancelBookingRequestDTO request);

    BookingResponseDTO updateBookingDates(
            Long bookingId, LocalDateTime startDate, LocalDateTime endDate);
}
