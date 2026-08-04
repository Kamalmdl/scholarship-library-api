package org.kamal.library_management.service;

import org.junit.jupiter.api.Test;
import org.kamal.library_management.dto.request.OrderItemRequestDto;
import org.kamal.library_management.dto.request.OrderRequestDto;
import org.kamal.library_management.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class OrderServiceImplIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void createOrder_shouldRollback_whenOneItemHasInvalidBookId() {
        long ordersBefore = orderRepository.count();

        OrderRequestDto requestDto = OrderRequestDto.builder()
                .memberId(1L)
                .items(List.of(
                        OrderItemRequestDto.builder().bookId(1L).quantity(1).build(),
                        OrderItemRequestDto.builder().bookId(999L).quantity(1).build() // mövcud olmayan
                ))
                .build();

        assertThatThrownBy(() -> orderService.createOrder(requestDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Book not found with id: 999");

        long ordersAfter = orderRepository.count();

        assertThat(ordersAfter).isEqualTo(ordersBefore);
    }
}