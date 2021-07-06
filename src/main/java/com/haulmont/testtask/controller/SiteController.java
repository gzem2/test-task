package com.haulmont.testtask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

@Controller
public class SiteController {
    @GetMapping("/")
    public ModelAndView showIndex() {
        return new ModelAndView("redirect:/customers");
    }

    @GetMapping("/error")
    public ModelAndView showError() {
        return new ModelAndView("shared/error");
    }
}