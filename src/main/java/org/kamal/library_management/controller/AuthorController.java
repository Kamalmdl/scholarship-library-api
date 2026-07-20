package org.kamal.library_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kamal.library_management.dto.AuthorRequestDto;
import org.kamal.library_management.dto.AuthorResponseDto;
import org.kamal.library_management.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDto> create(@Valid @RequestBody AuthorRequestDto requestDto) {
        AuthorResponseDto created = authorService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> update(@PathVariable Long id,
                                                    @Valid @RequestBody AuthorRequestDto requestDto) {
        return ResponseEntity.ok(authorService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}