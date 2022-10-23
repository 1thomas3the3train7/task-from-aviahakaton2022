package com.example.tasksher.Exception;

public class NotValidRequestException extends RuntimeException{
    public NotValidRequestException() {
        super();
    }

    public NotValidRequestException(String message) {
        super(message);
    }

    public NotValidRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidRequestException(Throwable cause) {
        super(cause);
    }
}
