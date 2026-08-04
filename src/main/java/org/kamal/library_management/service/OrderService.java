package org.kamal.library_management.service;

import org.kamal.library_management.dto.request.OrderRequestDto;
import org.kamal.library_management.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto requestDto);

    List<OrderResponseDto> getAllOrders();
}