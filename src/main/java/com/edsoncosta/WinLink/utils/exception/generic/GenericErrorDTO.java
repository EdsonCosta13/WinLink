package com.edsoncosta.WinLink.utils.exception.generic;

import org.springframework.http.HttpStatus;

public record GenericErrorDTO(String message, HttpStatus status) {
}
