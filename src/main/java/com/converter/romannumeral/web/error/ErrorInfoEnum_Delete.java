package com.converter.romannumeral.web.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorInfoEnum_Delete
{
    INVALID_DATA("INVALID_DATA", "Invalid input, please enter an integer value in the range from 1 to 3999",
                 HttpStatus.BAD_REQUEST),
    INVALID_DATA_FORMAT("INVALID_DATA_FORMAT",
                        "Invalid data format, please enter an integer value in the range from 1 to 3999",
                        HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR",
                          "Please try again later, we're working on making this experience better",
                          HttpStatus.INTERNAL_SERVER_ERROR);

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    ErrorInfoEnum_Delete(String errorCode, String errorMessage, HttpStatus httpStatus)
    {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
