package com.optiflow.exceptions.handler;

import com.optiflow.exceptions.custom.ClientNotFoundException;
import com.optiflow.exceptions.custom.PrescriptionNotFoundException;
import com.optiflow.exceptions.custom.ProductNotFoundException;
import com.optiflow.exceptions.custom.SaleNotFoundException;
import com.optiflow.exceptions.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClientNotFound(ClientNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                404,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(PrescriptionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePrescriptionNotFound(PrescriptionNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                404,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                404,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(SaleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSaleNotFound(SaleNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                404,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {

        ErrorResponse error = new ErrorResponse(
                500,
                "Internal server error",
                LocalDateTime.now()
        );

        return ResponseEntity.status(500).body(error);
    }

}
