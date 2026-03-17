package com.bhanu.service;

import com.bhanu.entity.ClickEvent;
import com.bhanu.entity.ShortLink;
import com.bhanu.repository.ClickEventRepository;
import com.bhanu.repository.ShortLinkRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RedirectService {

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    @Autowired
    private ClickEventRepository clickRepository;

    @Autowired
    private GeoIPService geoIPService;

    public String redirect(String code, HttpServletRequest request){

        ShortLink link = shortLinkRepository
                .findByShortCode(code)
                .orElseThrow();

        link.setClickCount(link.getClickCount()+1);

        shortLinkRepository.save(link);

        String ip = request.getRemoteAddr();

        String country = geoIPService.getCountry("8.8.8.8");

        ClickEvent event = new ClickEvent();

        event.setShortLink(link);
        event.setCountry(country);
        event.setDevice(request.getHeader("User-Agent"));
        event.setClickedAt(LocalDateTime.now());

        clickRepository.save(event);

        return link.getOriginalUrl();
    }


}
