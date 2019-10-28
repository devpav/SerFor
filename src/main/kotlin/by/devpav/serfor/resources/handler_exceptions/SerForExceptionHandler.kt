package by.devpav.serfor.resources.handler_exceptions

import by.devpav.serfor.exceptions.EntityNotFoundException
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class SerForExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handler(ex: EntityNotFoundException): ResponseEntity<HttpException> {
        val httpException = HttpException(ex.message, NOT_FOUND.value())
        return ResponseEntity
                .status(NOT_FOUND)
                .body(httpException)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handler(ex: RuntimeException): ResponseEntity<HttpException> {
        val httpException = HttpException(ex.message, BAD_REQUEST.value())
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(httpException)
    }

    @ExceptionHandler(Exception::class)
    fun handler(ex: Exception): ResponseEntity<HttpException> {
        val httpException = HttpException(ex.message, BAD_REQUEST.value())
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(httpException)
    }


    data class HttpException(val reason: String?, val httpStatus: Int)

}
