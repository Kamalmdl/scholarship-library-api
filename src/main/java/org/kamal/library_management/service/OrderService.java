package org.kamal.library_management.service;

import org.kamal.library_management.dto.request.OrderRequestDto;
import org.kamal.library_management.dto.response.OrderResponseDto;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto requestDto);
}