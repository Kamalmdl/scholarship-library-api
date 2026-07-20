package org.kamal.library_management.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamal.library_management.dto.AuthorRequestDto;
import org.kamal.library_management.dto.AuthorResponseDto;
import org.kamal.library_management.entity.Author;
import org.kamal.library_management.exceptions.ResourceNotFoundException;
import org.kamal.library_management.repository.AuthorRepository;
import org.kamal.library_management.service.impl.AuthorServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;
    private AuthorRequestDto requestDto;

    @BeforeEach
    void setUp() {
        author = Author.builder()
                .id(1L)
                .fullName("Chinghiz Abdullayev")
                .email("chinghiz@example.com")
                .build();

        requestDto = AuthorRequestDto.builder()
                .fullName("Chinghiz Abdullayev")
                .email("chinghiz@example.com")
                .build();
    }

    @Test
    void create_shouldSaveAuthorAndReturnResponseDto() {
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorResponseDto result = authorService.create(requestDto);

        assertThat(result).isNotNull();
        assertThat(result.getFullName()).isEqualTo("Chinghiz Abdullayev");
        assertThat(result.getEmail()).isEqualTo("chinghiz@example.com");
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void getById_shouldReturnAuthor_whenAuthorExists() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        AuthorResponseDto result = authorService.getById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getFullName()).isEqualTo("Chinghiz Abdullayev");
    }

    @Test
    void getById_shouldThrowException_whenAuthorNotFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorService.getById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Author not found with id: 99");
    }

    @Test
    void delete_shouldRemoveAuthor_whenAuthorExists() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        authorService.delete(1L);

        verify(authorRepository, times(1)).delete(author);
    }
}