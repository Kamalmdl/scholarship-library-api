package org.kamal.library_management.dto;

import lombok.*;

import java.time.Year;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {

    private Long id;
    private String title;
    private String isbn;
    private Year publishedYear;
    private Long authorId;
    private String authorName;
}