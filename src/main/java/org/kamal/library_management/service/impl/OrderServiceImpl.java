package org.kamal.library_management.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kamal.library_management.dto.request.OrderRequestDto;
import org.kamal.library_management.dto.response.OrderResponseDto;
import org.kamal.library_management.entity.Book;
import org.kamal.library_management.entity.Member;
import org.kamal.library_management.entity.Order;
import org.kamal.library_management.entity.OrderItem;
import org.kamal.library_management.exceptions.ResourceNotFoundException;
import org.kamal.library_management.repository.BookRepository;
import org.kamal.library_management.repository.MemberRepository;
import org.kamal.library_management.repository.OrderRepository;
import org.kamal.library_management.service.EmailNotificationService;
import org.kamal.library_management.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final EmailNotificationService emailNotificationService;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Member not found with id: " + requestDto.getMemberId()));

        Order order = Order.builder()
                .member(member)
                .orderDate(LocalDateTime.now())
                .items(new ArrayList<>())
                .build();

        List<String> bookTitles = new ArrayList<>();

        requestDto.getItems().forEach(itemDto -> {
            Book book = bookRepository.findById(itemDto.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Book not found with id: " + itemDto.getBookId()));

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .book(book)
                    .quantity(itemDto.getQuantity())
                    .build();

            order.getItems().add(orderItem);
            bookTitles.add(book.getTitle());
        });

        Order saved = orderRepository.save(order);

        log.info("Order {} saved, returning response to client now", saved.getId());
        emailNotificationService.sendOrderConfirmationEmail(member.getEmail(), saved.getId());

        return OrderResponseDto.builder()
                .id(saved.getId())
                .memberId(saved.getMember().getId())
                .orderDate(saved.getOrderDate())
                .bookTitles(bookTitles)
                .build();
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> OrderResponseDto.builder()
                        .id(order.getId())
                        .memberId(order.getMember().getId())
                        .orderDate(order.getOrderDate())
                        .bookTitles(order.getItems().stream()
                                .map(item -> item.getBook().getTitle())
                                .toList())
                        .build())
                .toList();
    }
}