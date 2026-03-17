package com.bhanu.controller;

import com.bhanu.entity.ShortLink;
import com.bhanu.repository.ShortLinkRepository;
import com.bhanu.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/links")
public class QRController {

    @Autowired
    private QRCodeService qrService;

    @Autowired
    private ShortLinkRepository repository;

    @GetMapping("/{id}/qr")
    public ResponseEntity<byte[]> qr(@PathVariable Long id){

        ShortLink link = repository.findById(id).orElseThrow();

        byte[] qr = qrService.generateQR(
                "http://localhost:8080/"+link.getShortCode()
        );

        return ResponseEntity.ok()
                .header("Content-Type","image/png")
                .body(qr);
    }
}