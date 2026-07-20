package org.kamal.library_management.service.impl;

import lombok.RequiredArgsConstructor;
import org.kamal.library_management.dto.BookRequestDto;
import org.kamal.library_management.dto.BookResponseDto;
import org.kamal.library_management.entity.Author;
import org.kamal.library_management.entity.Book;
import org.kamal.library_management.repository.AuthorRepository;
import org.kamal.library_management.repository.BookRepository;
import org.kamal.library_management.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public BookResponseDto create(BookRequestDto requestDto) {
        Author author = authorRepository.findById(requestDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + requestDto.getAuthorId()));

        Book book = Book.builder()
                .title(requestDto.getTitle())
                .isbn(requestDto.getIsbn())
                .publishedYear(requestDto.getPublishedYear())
                .author(author)
                .build();

        Book saved = bookRepository.save(book);
        return toResponseDto(saved);
    }

    @Override
    public BookResponseDto getById(Long id) {
        Book book = findBookOrThrow(id);
        return toResponseDto(book);
    }

    @Override
    public List<BookResponseDto> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto requestDto) {
        Book book = findBookOrThrow(id);

        Author author = authorRepository.findById(requestDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + requestDto.getAuthorId()));

        book.setTitle(requestDto.getTitle());
        book.setIsbn(requestDto.getIsbn());
        book.setPublishedYear(requestDto.getPublishedYear());
        book.setAuthor(author);

        Book updated = bookRepository.save(book);
        return toResponseDto(updated);
    }

    @Override
    public void delete(Long id) {
        Book book = findBookOrThrow(id);
        bookRepository.delete(book);
    }

    private Book findBookOrThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    private BookResponseDto toResponseDto(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publishedYear(book.getPublishedYear())
                .authorId(book.getAuthor().getId())
                .authorName(book.getAuthor().getFullName())
                .build();
    }
}