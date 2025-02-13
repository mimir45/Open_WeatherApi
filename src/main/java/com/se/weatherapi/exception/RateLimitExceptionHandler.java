package com.se.weatherapi.exception;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class RateLimitExceptionHandler extends RuntimeException {
    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<String> RateLimitExceptionHandler(RequestNotPermitted ex) {
        return ResponseEntity.status(429).body("Too many requests! Please try again later.");


  }
}
