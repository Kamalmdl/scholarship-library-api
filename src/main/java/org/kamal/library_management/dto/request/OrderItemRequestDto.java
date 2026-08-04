package org.kamal.library_management.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequestDto {

    @NotNull(message = "Book id is required")
    private Long bookId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}