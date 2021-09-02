package com.converter.romannumeral.web.handler;

import javax.validation.ConstraintViolationException;

import com.converter.romannumeral.web.dto.ErrorResponseDTO;
import com.converter.romannumeral.web.error.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Rest Controller advice for handling different types of exceptions
 * 1. ConstraintViolationException
 * 2. MethodArgumentTypeMismatchException
 * 3. RuntimeException
 *
 * @author sselvaraj
 */
@RestControllerAdvice
public class RestExceptionHandler
{
    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Exception handler method to handle ConstraintViolationException type and returns a error response
     *
     * @param constraintViolationException
     * @return ResponseEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(
            ConstraintViolationException constraintViolationException)
    {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
                                                                 ErrorMessage.INVALID_DATA);
        LOG.error(errorResponseDTO.toString(), constraintViolationException);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler method to handle MethodArgumentTypeMismatchException type and returns a error response
     *
     * @param typeMismatchException
     * @return ResponseEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException typeMismatchException)
    {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
                                                                 ErrorMessage.INPUT_TYPE_MISMATCH);
        LOG.error(errorResponseDTO.toString(), typeMismatchException);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler method to handle RuntimeException type and returns a error response
     *
     * @param runtimeException
     * @return ResponseEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException runtimeException)
    {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                                 ErrorMessage.INTERNAL_SERVER_ERROR);
        LOG.error(errorResponseDTO.toString(), runtimeException);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
