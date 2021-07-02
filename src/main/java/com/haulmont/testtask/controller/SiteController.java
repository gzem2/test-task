package com.haulmont.testtask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SiteController {
    @GetMapping("/")
    public String showIndex() {
        return "index";
    }
}