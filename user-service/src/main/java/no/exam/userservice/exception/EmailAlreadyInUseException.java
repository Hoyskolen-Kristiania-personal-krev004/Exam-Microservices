package no.exam.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyInUseException extends RuntimeException{
    private String message;

    public EmailAlreadyInUseException(String message){
        super(message);
    }
}
