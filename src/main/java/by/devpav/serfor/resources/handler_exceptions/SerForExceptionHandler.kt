package by.devpav.serfor.resources.handler_exceptions;

import by.devpav.serfor.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class SerForExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HttpException> handler(EntityNotFoundException ex) {
        final HttpException httpException = new HttpException(ex.getMessage(), NOT_FOUND);
        return ResponseEntity
                .status(NOT_FOUND)
                .body(httpException);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HttpException> handler(RuntimeException ex) {
        final HttpException httpException = new HttpException(ex.getMessage(), BAD_REQUEST);
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(httpException);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpException> handler(Exception ex) {
        final HttpException httpException = new HttpException(ex.getMessage(), BAD_REQUEST);
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(httpException);
    }



    private static class HttpException {
        private String reason;
        private HttpStatus httpStatus;

        public HttpException(String reason, HttpStatus httpStatus) {
            this.reason = reason;
            this.httpStatus = httpStatus;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public void setHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
        }
    }

}
