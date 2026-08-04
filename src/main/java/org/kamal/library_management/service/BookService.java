package org.kamal.library_management.service;

import org.kamal.library_management.dto.request.BookRequestDto;
import org.kamal.library_management.dto.response.BookResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookResponseDto create(BookRequestDto requestDto);

    BookResponseDto getById(Long id);

    Page<BookResponseDto> getAll(Pageable pageable);

    BookResponseDto update(Long id, BookRequestDto requestDto);

    void delete(Long id);
}