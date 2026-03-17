package com.bhanu.controller;

import com.bhanu.entity.ClickEvent;
import com.bhanu.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/links")
public class AnalyticsController {

    @Autowired
    private AnalyticsService service;

    @GetMapping("/{id}/analytics")
    public List<ClickEvent> analytics(@PathVariable Long id){

        return service.analytics(id);
    }

    @GetMapping("/{id}/clicks/geo")
    public Map<String,Long> geo(@PathVariable Long id){

        return service.countryBreakdown(id);
    }

    @GetMapping("/{id}/clicks/devices")
    public Map<String,Long> devices(@PathVariable Long id){

        return service.deviceBreakdown(id);
    }
}
