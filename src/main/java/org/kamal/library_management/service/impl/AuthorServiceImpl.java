package org.kamal.library_management.service.impl;

import lombok.RequiredArgsConstructor;
import org.kamal.library_management.dto.AuthorRequestDto;
import org.kamal.library_management.dto.AuthorResponseDto;
import org.kamal.library_management.entity.Author;
import org.kamal.library_management.repository.AuthorRepository;
import org.kamal.library_management.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorResponseDto create(AuthorRequestDto requestDto) {
        Author author = Author.builder()
                .fullName(requestDto.getFullName())
                .email(requestDto.getEmail())
                .build();

        Author saved = authorRepository.save(author);
        return toResponseDto(saved);
    }

    @Override
    public AuthorResponseDto getById(Long id) {
        Author author = findAuthorOrThrow(id);
        return toResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public AuthorResponseDto update(Long id, AuthorRequestDto requestDto) {
        Author author = findAuthorOrThrow(id);
        author.setFullName(requestDto.getFullName());
        author.setEmail(requestDto.getEmail());

        Author updated = authorRepository.save(author);
        return toResponseDto(updated);
    }

    @Override
    public void delete(Long id) {
        Author author = findAuthorOrThrow(id);
        authorRepository.delete(author);
    }

    private Author findAuthorOrThrow(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    private AuthorResponseDto toResponseDto(Author author) {
        return AuthorResponseDto.builder()
                .id(author.getId())
                .fullName(author.getFullName())
                .email(author.getEmail())
                .bookCount(author.getBooks() != null ? author.getBooks().size() : 0)
                .build();
    }
}