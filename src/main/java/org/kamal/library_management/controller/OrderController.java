package org.kamal.library_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kamal.library_management.dto.request.OrderRequestDto;
import org.kamal.library_management.dto.response.OrderResponseDto;
import org.kamal.library_management.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@Valid @RequestBody OrderRequestDto requestDto) {
        OrderResponseDto created = orderService.createOrder(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}