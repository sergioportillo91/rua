package com.aquacarburos.rua.infrastructure.rest.handler;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {

    private String code;

    private String message;

    private LocalDateTime timestamp;

    private Map<String, String> details;
}