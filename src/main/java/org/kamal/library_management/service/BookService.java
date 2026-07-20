package org.kamal.library_management.service;

import org.kamal.library_management.dto.BookRequestDto;
import org.kamal.library_management.dto.BookResponseDto;

import java.util.List;

public interface BookService {

    BookResponseDto create(BookRequestDto requestDto);

    BookResponseDto getById(Long id);

    List<BookResponseDto> getAll();

    BookResponseDto update(Long id, BookRequestDto requestDto);

    void delete(Long id);
}