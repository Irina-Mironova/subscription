package ru.irina.subscription.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppError {
    private int statusCode;

    private String message;

    private List<String> errors;

    public AppError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
