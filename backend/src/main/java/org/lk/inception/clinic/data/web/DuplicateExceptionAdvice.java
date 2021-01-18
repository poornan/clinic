package org.lk.inception.clinic.data.web;

import org.lk.inception.clinic.data.service.DuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DuplicateExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String holidayExceptionHandler(DuplicateException exception) {
        return exception.getMessage() + " record already exists.";
    }
}
