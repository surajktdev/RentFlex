package com.rentflex.bookingservice.controller;

import com.rentflex.bookingservice.dto.BookingRequestDTO;
import com.rentflex.bookingservice.dto.BookingResponseDTO;
import com.rentflex.bookingservice.dto.CancelBookingRequestDTO;
import com.rentflex.bookingservice.service.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
@Tag(
        name = "Booking Operations",
        description = "Endpoints for handling booking category-related functionalities")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/")
    public ResponseEntity<BookingResponseDTO> createBooking(
            @RequestBody BookingRequestDTO request) {
        BookingResponseDTO response = bookingService.createBooking(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        BookingResponseDTO response = bookingService.getBookingById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUserId(@PathVariable Long userId) {
        List<BookingResponseDTO> bookings = bookingService.getBookingsByUser(userId);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/cancel")
    public ResponseEntity<BookingResponseDTO> cancelBooking(
            @RequestBody CancelBookingRequestDTO request) {

        BookingResponseDTO response = bookingService.cancelBooking(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<BookingResponseDTO> updateBookingDates(
            @PathVariable Long id,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {

        BookingResponseDTO response =
                bookingService.updateBookingDates(
                        id, startDate.atStartOfDay(), endDate.atStartOfDay());
        return ResponseEntity.ok(response);
    }
}
