package com.bigbata.craftsman.exceptionHandler;

import com.bigbata.craftsman.exception.MessageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lixianghui on 15-2-10.
 */
@ControllerAdvice
public class RestExceptionHandler {
    static Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(MessageException.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo MessageExcetpionHandler(HttpServletRequest req, MessageException ex) {

        return new ErrorInfo("500", "500001",ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo DefaultExcetpionHandler(HttpServletRequest req, Exception ex) {

        log.error("Exception in Platform is :",ex);
        return new ErrorInfo("500", "500002", "未知错误");
    }
    
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo DefaultNullPointExcetpionHandler(HttpServletRequest req, NullPointerException ex) {

        log.error("Exception in Platform is :",ex);
        return new ErrorInfo("500", "500003", "空指针错误");
    }
}
