package com.evgen.controller;

import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionController {


    @ExceptionHandler(NoHandlerFoundException.class)
    public String handler404(Exception ex,
                             HttpServletRequest req,
                             Model model) {

        System.out.println("Request: " + req.getRequestURL());
        System.out.println("Exception: " + ex);

        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("exception", ex);

        return "/error404";
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public String handler403(Exception ex,
                             HttpServletRequest req,
                             Model model) {

        System.out.println("Request: " + req.getRequestURL());
        System.out.println("Exception: " + ex);

        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("exception", ex);

        return "/error403";
    }

    @ExceptionHandler(Exception.class)
    public String handler500(Exception ex,
                             HttpServletRequest req,
                             Model model) {

        System.out.println("Request: " + req.getRequestURL());
        System.out.println("Exception: " + ex);

        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("exception", ex);

        return "/error500";
    }


}
