package org.lk.inception.clinic.data.web;

import org.lk.inception.clinic.data.service.HolidayException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HolidayExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(HolidayException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String holidayExceptionHandler(HolidayException exception) {
        return exception.getMessage() + " is on a HOLIDAY!";
    }
}
