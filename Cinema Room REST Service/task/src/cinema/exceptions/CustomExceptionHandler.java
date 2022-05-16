package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WrongRowColumnException.class)
    public ResponseEntity<ExceptionResponse> rowColumnHandleExc() {
        return new ResponseEntity<>(new ExceptionResponse("The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketIsBoughtException.class)
    public ResponseEntity<ExceptionResponse> ticketHandleExc() {
        return new ResponseEntity<>(new ExceptionResponse("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<ExceptionResponse> wrongTokenHandleExc() {
        return new ResponseEntity<>(new ExceptionResponse("Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordWrongException.class)
    public ResponseEntity<ExceptionResponse> wrongPasswordHandleExc() {
        return new ResponseEntity<>(new ExceptionResponse("The password is wrong!"), HttpStatus.UNAUTHORIZED);
    }
}
