package com.bhanu.controller;


import com.bhanu.service.RedirectService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/redirect")
public class RedirectController {

    @Autowired
    private RedirectService redirectService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode,
                                      HttpServletRequest request){

        String url = redirectService.redirect(shortCode,request);

        return ResponseEntity
                .status(302)
                .location(URI.create(url))
                .build();
    }
}
