package com.bhanu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String HomeControllerUrl(){
        return "Home Controller of Url Shortner";
    }
}
