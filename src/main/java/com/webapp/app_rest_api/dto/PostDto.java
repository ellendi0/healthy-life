package com.webapp.app_rest_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Content cannot be empty")
    @Size(min = 1, max = 10000, message = "Content must be between 1 and 10000 characters")
    private String content;

    private Instant date;
}
