package com.rentflex.bookingservice.service.impl;

import com.rentflex.bookingservice.dto.BookingRequestDTO;
import com.rentflex.bookingservice.dto.BookingResponseDTO;
import com.rentflex.bookingservice.dto.CancelBookingRequestDTO;
import com.rentflex.bookingservice.model.Booking;
import com.rentflex.bookingservice.model.BookingStatus;
import com.rentflex.bookingservice.repository.BookingRepository;
import com.rentflex.bookingservice.repository.PaymentInfoRepository;
import com.rentflex.bookingservice.service.BookingService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PaymentInfoRepository paymentInfoRepository;

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO request) {
        if (request.userId() == null || request.itemId() == null) {
            throw new IllegalArgumentException("User ID and Item ID are required.");
        }

        if (request.startDate() == null || request.endDate() == null) {
            throw new IllegalArgumentException("Start and End dates are required.");
        }

        if (request.endDate().isBefore(request.startDate())) {
            throw new IllegalArgumentException("End date must be after start date.");
        }

        // pending work
        // Check user validity via UserService (Feign Client)
        // userClient.validateUser(request.getUserId());

        // Check item availability via InventoryService
        // inventoryClient.checkAvailability(request.getItemId(), request.getStartDate(),
        // request.getEndDate());

        Booking booking = new Booking();
        booking.setUserId(request.userId());
        booking.setItemId(request.itemId());
        booking.setStartDate(request.startDate());
        booking.setEndDate(request.endDate());
        booking.setStatus(request.status());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        Booking saved = bookingRepository.save(booking);
        return BookingResponseDTO.builder()
                .bookingId(saved.getId())
                .message("Booking created successfully")
                .build();
    }

    @Override
    public BookingResponseDTO getBookingById(Long bookingId) {
        Booking bookingDetail =
                bookingRepository
                        .findById(bookingId)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Booking not found with ID: " + bookingId));
        return BookingResponseDTO.builder()
                .bookingId(bookingDetail.getId())
                .status(bookingDetail.getStatus())
                .totalPrice(bookingDetail.getTotalPrice())
                .startDate(bookingDetail.getStartDate())
                .endDate(bookingDetail.getEndDate())
                .build();
    }

    @Override
    public List<BookingResponseDTO> getBookingsByUser(Long userId) {
        Booking bookingDetail =
                bookingRepository
                        .getBookingsByUser(userId)
                        .orElseThrow(
                                () -> new RuntimeException("Booking not found with ID: " + userId));

        return List.of(
                BookingResponseDTO.builder()
                        .bookingId(bookingDetail.getId())
                        .status(bookingDetail.getStatus())
                        .totalPrice(bookingDetail.getTotalPrice())
                        .startDate(bookingDetail.getStartDate())
                        .endDate(bookingDetail.getEndDate())
                        .build());
    }

    @Override
    public BookingResponseDTO cancelBooking(CancelBookingRequestDTO request) {

        Booking booking =
                bookingRepository
                        .findById(request.bookingId())
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Booking not found with ID: "
                                                        + request.bookingId()));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Booking is already cancelled.");
        }

        // Rule - Check if cancellation allowed
        if (booking.getStartDate().isBefore(LocalDate.now().atStartOfDay())) {
            throw new IllegalStateException("Cannot cancel booking after start date.");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        booking.setUpdatedAt(LocalDateTime.now());
        booking.setCancellationReason(request.reason());
        bookingRepository.save(booking);
        return BookingResponseDTO.builder()
                .bookingId(booking.getId())
                .status(booking.getStatus())
                .message("Booking cancelled successfully.")
                .updatedAt(booking.getUpdatedAt())
                .build();
    }

    @Override
    public BookingResponseDTO updateBookingDates(
            Long bookingId, LocalDateTime startDate, LocalDateTime endDate) {
        Booking bookingDetail =
                bookingRepository
                        .findById(bookingId)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Booking not found with ID: " + bookingId));

        bookingDetail.setStartDate(startDate);
        bookingDetail.setEndDate(endDate);
        return BookingResponseDTO.builder()
                .bookingId(bookingDetail.getId())
                .status(bookingDetail.getStatus())
                .totalPrice(bookingDetail.getTotalPrice())
                .startDate(bookingDetail.getStartDate())
                .endDate(bookingDetail.getEndDate())
                .message("Date updated successfully.")
                .build();
    }
}
