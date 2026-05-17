package com.yp.ahrenix.exception;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private boolean success;

    private String message;

    private List<String> errors;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

}
