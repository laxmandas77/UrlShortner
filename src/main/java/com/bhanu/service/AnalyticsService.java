package com.bhanu.service;

import com.bhanu.entity.ClickEvent;
import com.bhanu.repository.ClickEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    @Autowired
    private ClickEventRepository repository;

    public List<ClickEvent> analytics(Long linkId){

        return repository.findByShortLinkId(linkId);
    }

    public Map<String,Long> countryBreakdown(Long linkId){

        return repository.findByShortLinkId(linkId)
                .stream()
                .collect(Collectors.groupingBy(
                        ClickEvent::getCountry,
                        Collectors.counting()
                ));
    }

    public Map<String,Long> deviceBreakdown(Long linkId){

        return repository.findByShortLinkId(linkId)
                .stream()
                .collect(Collectors.groupingBy(
                        ClickEvent::getDevice,
                        Collectors.counting()
                ));
    }
}
