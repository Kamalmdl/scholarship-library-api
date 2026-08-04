package org.kamal.library_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kamal.library_management.dto.Request.BookRequestDto;
import org.kamal.library_management.dto.Response.BookResponseDto;
import org.kamal.library_management.entity.Book;
import org.kamal.library_management.repository.BookRepository;
import org.kamal.library_management.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<BookResponseDto> create(@Valid @RequestBody BookRequestDto requestDto) {
        BookResponseDto created = bookService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<BookResponseDto>> getAll(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(bookService.getAll(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String keyword) {
        return ResponseEntity.ok(bookRepository.searchByTitleKeyword(keyword));
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<Book>> getByCategory(@RequestParam String categoryName) {
        return ResponseEntity.ok(bookRepository.findByCategories_NameIgnoreCase(categoryName));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> update(@PathVariable Long id,
                                                  @Valid @RequestBody BookRequestDto requestDto) {
        return ResponseEntity.ok(bookService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}