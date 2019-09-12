package com.evgen.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionController {

    private static final Logger logger = Logger.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handler404(Exception ex,
                             HttpServletRequest req,
                             Model model) {

        logger.error("Request: " + req.getRequestURL());
        logger.error("Exception: " + ex);

        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("exception", ex);

        return "/error404";
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public String handler403(Exception ex,
                             HttpServletRequest req,
                             Model model) {

        logger.error("Request: " + req.getRequestURL());
        logger.error("Exception: " + ex);

        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("exception", ex);

        return "/error403";
    }

    @ExceptionHandler(Exception.class)
    public String handler500(Exception ex,
                             HttpServletRequest req,
                             Model model) {

        logger.error("Request: " + req.getRequestURL());
        logger.error("Exception: " + ex);

        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("exception", ex);

        return "/error500";
    }


}
