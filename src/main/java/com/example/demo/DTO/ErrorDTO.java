package com.example.demo.DTO;

public class ErrorDTO extends DTO {
    String error;
    public ErrorDTO(String message) {
        this.error =message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
