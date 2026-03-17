package com.bhanu.controller;

import com.bhanu.dto.CreateShortLinkRequest;
import com.bhanu.entity.ShortLink;
import com.bhanu.entity.User;
import com.bhanu.service.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/links")
public class ShortLinkController {

    @Autowired
    private ShortLinkService service;

    @PostMapping
    public ShortLink create(@RequestBody CreateShortLinkRequest request){

        User user = new User();
        user.setId(request.getUserId());

        return service.createLink(request.getUrl(), user);
    }


    @GetMapping
    public List<ShortLink> getLinks(){

        return service.getUserLinks(1L);
    }

    @PutMapping("/{id}")
    public ShortLink update(@PathVariable Long id,
                            @RequestBody Map<String,String> body){

        return service.updateLink(
                id,
                body.get("alias"),
                LocalDateTime.parse(body.get("expiry"))
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

        service.deleteLink(id);
    }
}